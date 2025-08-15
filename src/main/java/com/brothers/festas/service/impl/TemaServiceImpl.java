package com.brothers.festas.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.request.TemaUpdateRequestDTO;
import com.brothers.festas.dto.response.ImagemResponseDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Imagem;
import com.brothers.festas.model.Tema;
import com.brothers.festas.repository.ImagemRepository;
import com.brothers.festas.repository.TemaRepository;
import com.brothers.festas.service.ITemaService;
import com.brothers.festas.util.ContratoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TemaServiceImpl implements ITemaService {

    private final TemaRepository temaRepository;
    private final ContratoMapper contratoMapper;
    private final AmazonS3 s3client;
    private final ImagemRepository imagemRepository;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    public TemaServiceImpl(AmazonS3 s3client, ContratoMapper contratoMapper, TemaRepository temaRepository, ImagemRepository imagemRepository) {
        this.s3client = s3client;
        this.contratoMapper = contratoMapper;
        this.temaRepository = temaRepository;
        this.imagemRepository = imagemRepository;
    }

    public TemaResponseDTO criarTema(TemaRequestDTO request) {
        Tema tema = new Tema();
        tema.setDescricao(request.getDescricao());
        tema.setObservacoes(request.getObservacoes());

        return new TemaResponseDTO(temaRepository.save(tema));
    }

    @Override
    public TemaResponseDTO findById(Long id) {
        Tema tema = returnTema(id);
        TemaResponseDTO responseDTO = contratoMapper.toTemaResponseDTO(tema);

        // Busca as imagens associadas a este tema no banco de dados
        List<Imagem> imagensDoTema = imagemRepository.findByTemaId(tema.getId()); // Crie este método no ImagemRepository

        // Mapeia as entidades Imagem para ImagemResponseDTOs e gera as URLs pré-assinadas
        List<ImagemResponseDTO> imagemDTOs = imagensDoTema.stream()
                .map(imagem -> {
                    String s3Key = extractS3KeyFromUrl(imagem.getUrl()); // Extrai a chave do S3 da URL salva
                    String presignedUrl = generatePresignedUrl(s3Key); // Gera a URL pré-assinada

                    // Cria e retorna o ImagemResponseDTO
                    ImagemResponseDTO imagemResponseDTO = new ImagemResponseDTO();
                    imagemResponseDTO.setId(imagem.getId());
                    imagemResponseDTO.setUrl(presignedUrl);
                    imagemResponseDTO.setDescricao(imagem.getDescricao());
                    imagemResponseDTO.setNomeArquivoOriginal(imagem.getNomeArquivoOriginal());
                    return imagemResponseDTO;
                })
                .collect(Collectors.toList());

        responseDTO.setImagens(imagemDTOs); // Define a lista de ImagemResponseDTOs no TemaResponseDTO

        return responseDTO;
    }

    @Override
    public Page<TemaResponseDTO> findAllByFilters(Pageable pageable, String descricao) {
        Page<Tema> page = (descricao != null && !descricao.isBlank())
                ? temaRepository.findByDescricaoContainingIgnoreCase(descricao, pageable)
                : temaRepository.findAll(pageable);

        return page.map(tema -> {
            TemaResponseDTO responseDTO = contratoMapper.toTemaResponseDTO(tema);
            // Repete a lógica de busca de imagens para cada tema na página
            List<Imagem> imagensDoTema = imagemRepository.findByTemaId(tema.getId());
            List<ImagemResponseDTO> imagemDTOs = imagensDoTema.stream()
                    .map(imagem -> {
                        String s3Key = extractS3KeyFromUrl(imagem.getUrl());
                        String presignedUrl = generatePresignedUrl(s3Key);

                        ImagemResponseDTO imagemResponseDTO = new ImagemResponseDTO();
                        imagemResponseDTO.setId(imagem.getId());
                        imagemResponseDTO.setUrl(presignedUrl);
                        imagemResponseDTO.setDescricao(imagem.getDescricao());
                        imagemResponseDTO.setNomeArquivoOriginal(imagem.getNomeArquivoOriginal());
                        return imagemResponseDTO;
                    })
                    .collect(Collectors.toList());
            responseDTO.setImagens(imagemDTOs);
            return responseDTO;
        });
    }

    @Override
    public String uploadFile(Long temaId, MultipartFile file) { // AGORA RECEBE O TEMA ID
        if (temaId == null) {
            throw new IllegalArgumentException("O ID do tema não pode ser nulo para o upload da imagem.");
        }
        String uniqueFileName = generateFileName(file);
        String s3Key = "temas/" + temaId + "/" + uniqueFileName;

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            s3client.putObject(new PutObjectRequest(bucketName, s3Key, file.getInputStream(), metadata));

            return s3client.getUrl(bucketName, s3Key).toString();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload do arquivo para o S3: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletarTema(Long id) {
        Tema tema = temaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tema não encontrado"));
        temaRepository.delete(tema);
    }

    @Override
    public void deleteFile(String fileUrl) {
        String s3Key;
        try {
            // Analisa a URL para obter o path
            java.net.URL url = new java.net.URL(fileUrl);
            s3Key = url.getPath();

            if (s3Key.startsWith("/")) {
                s3Key = s3Key.substring(1);
            }

            if (s3Key.isBlank()) {
                throw new IllegalArgumentException("Não foi possível extrair a chave do S3 da URL: " + fileUrl);
            }

            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, s3Key);

            s3client.deleteObject(deleteObjectRequest);
            System.out.println("Arquivo S3 " + s3Key + " excluído com sucesso do bucket " + bucketName);

        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException("URL da imagem S3 malformada: " + fileUrl, e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o arquivo S3 com a URL " + fileUrl + ": " + e.getMessage(), e);
        }
    }

    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + "-" + file.getOriginalFilename().replace(" ", "_");
    }

    // Método auxiliar para extrair a chave do S3 de uma URL completa
    private String extractS3KeyFromUrl(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            String path = url.getPath();
            // Remove a barra inicial se existir
            return path.startsWith("/") ? path.substring(1) : path;
        } catch (MalformedURLException e) {
            System.err.println("URL malformada ao extrair chave S3: " + fileUrl + ". Erro: " + e.getMessage());
            // Dependendo do seu tratamento de erro, você pode lançar uma exceção ou retornar null/vazio
            throw new RuntimeException("URL da imagem S3 malformada ao extrair chave: " + fileUrl, e);
        }
    }

    // Método auxiliar para gerar URL pré-assinada
    private String generatePresignedUrl(String s3Key) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60; // 1 hora de validade para a URL
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, s3Key)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);

        return s3client.generatePresignedUrl(generatePresignedUrlRequest).toExternalForm();
    }

    private Tema returnTema(Long id) {
        return temaRepository.findById(id)
                .orElseThrow(()-> new ServiceException("Tema não encontrado no banco de dados!"));
    }
    @Override
    public TemaResponseDTO atualizarTema(Long id, TemaUpdateRequestDTO request) {
        Tema tema = temaRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Tema não encontrado com ID: " + id));

        if (request.getDescricao() != null) {
            tema.setDescricao(request.getDescricao());
        }

        if (request.getObservacoes() != null) {
            tema.setObservacoes(request.getObservacoes());
        }

        return new TemaResponseDTO(temaRepository.save(tema));
    }

}
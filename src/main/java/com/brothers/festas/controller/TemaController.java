package com.brothers.festas.controller;

import com.brothers.festas.dto.response.ImagemResponseDTO;
import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.request.TemaUpdateRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import com.brothers.festas.model.Imagem;
import com.brothers.festas.model.Tema;
import com.brothers.festas.repository.ImagemRepository;
import com.brothers.festas.repository.TemaRepository;
import com.brothers.festas.service.ITemaService;
import com.brothers.festas.util.ContratoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/tema")
public class TemaController {
    private final ITemaService iTemaService;
    private final TemaRepository temaRepository;
    private final ImagemRepository imagemRepository;
    private final ContratoMapper contratoMapper;

    public TemaController(TemaRepository temaRepository, ITemaService iTemaService, ImagemRepository imagemRepository, ContratoMapper contratoMapper) {
        this.temaRepository = temaRepository;
        this.iTemaService = iTemaService;
        this.imagemRepository = imagemRepository;
        this.contratoMapper = contratoMapper;
    }

    @PostMapping
    public ResponseEntity<TemaResponseDTO> criar(@RequestBody TemaRequestDTO request) {
        return ResponseEntity.ok(iTemaService.criarTema(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(iTemaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<TemaResponseDTO>> findAllByFilters(Pageable pageable,
                                                         @RequestParam(name = "descricao", required = false) String descricao) {
        return ResponseEntity.ok().body(iTemaService.findAllByFilters(pageable, descricao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemaResponseDTO> atualizar(@PathVariable Long id,
                                                     @RequestBody TemaUpdateRequestDTO request) {
        return ResponseEntity.ok(iTemaService.atualizarTema(id, request));
    }

    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<ImagemResponseDTO> uploadTemaImage(@PathVariable Long id,
                                                             @RequestParam("file") MultipartFile file,
                                                             @RequestParam(value = "descricao", required = false) String descricao) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Tema tema = temaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Tema não encontrado"));

            String imageUrl = iTemaService.uploadFile(tema.getId(), file);

            Imagem imagem = new Imagem();
            imagem.setUrl(imageUrl);
            imagem.setDescricao(descricao);
            imagem.setNomeArquivoOriginal(file.getOriginalFilename());
            imagem.setTema(tema);

            Imagem savedImagem = imagemRepository.save(imagem);

            tema.getImagens().add(savedImagem);
            temaRepository.save(tema);

            return ResponseEntity.status(HttpStatus.CREATED).body(contratoMapper.toImagemResponseDTO(savedImagem));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        try {
            Imagem imagem = imagemRepository.findById(imageId)
                    .orElseThrow(() -> new RuntimeException("Imagem com ID " + imageId + " não encontrada."));

            iTemaService.deleteFile(imagem.getUrl());
            imagemRepository.delete(imagem);

            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrada")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            System.err.println("Erro inesperado ao tentar deletar imagem: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            System.err.println("Erro ao deletar imagem: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
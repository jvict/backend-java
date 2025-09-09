package com.brothers.festas.util;

import com.brothers.festas.dto.request.*;
import com.brothers.festas.dto.response.*;
import com.brothers.festas.model.*;
import com.brothers.festas.repository.ClienteRepository;
import com.brothers.festas.repository.ControleFestaRepository;
import com.brothers.festas.repository.ItemContratoRepository;
import com.brothers.festas.repository.TemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContratoMapper {

    private final TemaRepository temaRepository;
    private final ClienteRepository clienteRepository;
    private final ItemContratoRepository itemContratoRepository;
    private final ControleFestaRepository controleFestaRepository; // <-- adicionado
    private final ClienteMapper clienteMapper;

    public Contrato toEntity(ContratoRequestDTO dto) {
        Contrato contrato = new Contrato();

        contrato.setValorRecebido(dto.getValorRecebido());
        contrato.setValorPendente(dto.getValorPendente());
        contrato.setValorTotal(dto.getValorTotal());

        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        contrato.setCliente(cliente);

        contrato.setTipoDoContrato(dto.getTipoDoContrato());
        contrato.setDataHoraInicial(dto.getDataHoraInicial());
        contrato.setDataHoraFinal(dto.getDataHoraFinal());
        contrato.setDuracao(dto.getDuracao());
        contrato.setQuantidadeConvidados(dto.getQuantidadeConvidados());
        contrato.setObservacoes(dto.getObservacoes());
        contrato.setDesconto(dto.getDesconto());
        contrato.setAcrescimo(dto.getAcrescimo());
        contrato.setSituacao(dto.getSituacao());

        List<ItemContrato> itensContrato = itemContratoRepository.findAllById(dto.getItensContrato());
        contrato.setItensContrato(itensContrato);

        List<Tema> temas = temaRepository.findAllById(dto.getTemas());
        contrato.setTemas(temas);

        List<Aniversariante> aniversariantes = dto.getAniversariantes().stream().map(aniversarianteDTO -> {
            Aniversariante aniversariante = new Aniversariante();
            aniversariante.setNomeAniversariante(aniversarianteDTO.getNomeAniversariante());
            aniversariante.setIdade(aniversarianteDTO.getIdade());
            aniversariante.setIdadeNoEvento(aniversarianteDTO.getIdadeNoEvento());
            aniversariante.setContrato(contrato);
            return aniversariante;
        }).collect(Collectors.toList());
        contrato.setAniversariantes(aniversariantes);

        // === VINCULA O CONTRATO AO CONTROLE DE FESTA ===
        if (dto.getIdControleFesta() != null) {
            ControleFesta controleFesta = controleFestaRepository.findById(dto.getIdControleFesta())
                    .orElseThrow(() -> new RuntimeException("ControleFesta não encontrado"));
            contrato.setControleFesta(controleFesta);
        }

        return contrato;
    }


    public ContratoResponseDTO toResponse(Contrato contrato) {
        Cliente clienteEntity = contrato.getCliente() != null
                ? clienteRepository.findById(contrato.getCliente().getId())
                .orElse(null)
                : null;
        ClienteResponseDTO cliente = clienteEntity != null ? clienteMapper.toClienteDTO(clienteEntity) : null;

        return ContratoResponseDTO.builder()
                .id(contrato.getId())
                .cliente(cliente)
                .situacao(contrato.getSituacao())
                .valorRecebido(contrato.getValorRecebido())
                .valorPendente(contrato.getValorPendente())
                .valorTotal(contrato.getValorTotal())
                .tipoDoContrato(contrato.getTipoDoContrato())
                .dataHoraInicial(contrato.getDataHoraInicial())
                .dataHoraFinal(contrato.getDataHoraFinal())
                .duracao(contrato.getDuracao())
                .quantidadeConvidados(contrato.getQuantidadeConvidados())
                .observacoes(contrato.getObservacoes())
                .desconto(contrato.getDesconto())
                .acrescimo(contrato.getAcrescimo())
                .dataCadastro(contrato.getDataCadastro())
                .dataAtualizacao(contrato.getDataAtualizacao())
                .itensContrato(contrato.getItensContrato() != null ? contrato.getItensContrato().stream()
                        .map(this::toItemContratoResponseDTO).toList() : List.of())
                .pagamentos(contrato.getPagamentos() != null ? contrato.getPagamentos().stream()
                        .map(this::toPagamentoResponseDTO).toList() : List.of())
                .temas(contrato.getTemas() != null ? contrato.getTemas().stream()
                        .map(this::toTemaResponseDTO).toList() : List.of())
                .aniversariantes(contrato.getAniversariantes() != null ? contrato.getAniversariantes().stream()
                        .map(this::toAniversarianteResponseDTO).toList() : List.of())
                .controleFesta(contrato.getControleFesta().getId())
                .build();
    }

    public ContratoCalendarioResponseDTO toResponseCalendario(Contrato contrato) {
        return ContratoCalendarioResponseDTO.builder()
                .id(contrato.getId())
                .nomeCliente(contrato.getCliente() != null ? contrato.getCliente().getNome() : null)
                .situacao(contrato.getSituacao())
                .valorRecebido(contrato.getValorRecebido())
                .valorPendente(contrato.getValorPendente())
                .valorTotal(contrato.getValorTotal())
                .dataHoraInicial(contrato.getDataHoraInicial())
                .dataHoraFinal(contrato.getDataHoraFinal())
                .build();
    }

    public TemaResponseDTO toTemaResponseDTO(Tema tema) {
        return TemaResponseDTO.builder()
                .id(tema.getId())
                .descricao(tema.getDescricao())
                .observacoes(tema.getObservacoes())
                .build();
    }

    public ItemContratoResponseDTO toItemContratoResponseDTO(ItemContrato itemContrato) {
        return ItemContratoResponseDTO.builder()
                .id(itemContrato.getId())
                .descricao(itemContrato.getDescricao())
                .valor(itemContrato.getValor())
                .build();
    }

    public AniversarianteResponseDTO toAniversarianteResponseDTO(Aniversariante aniversariante) {
        return AniversarianteResponseDTO.builder()
                .id(aniversariante.getId())
                .nomeAniversariante(aniversariante.getNomeAniversariante())
                .idade(aniversariante.getIdade())
                .idadeNoEvento(aniversariante.getIdadeNoEvento())
                .build();
    }

    private PagamentoResponseDTO toPagamentoResponseDTO(Pagamento pagamento) {
        return PagamentoResponseDTO.builder()
                .id(pagamento.getId())
                .valor(pagamento.getValor())
                .meioPagamento(pagamento.getMeioPagamento())
                .dataPagamento(pagamento.getDataPagamento())
                .recebido(pagamento.getRecebido())
                .observacoes(pagamento.getObservacoes())
                .contratoId(pagamento.getContrato().getId())
                .build();
    }

    public ImagemResponseDTO toImagemResponseDTO(Imagem imagem) {
        return ImagemResponseDTO.builder()
                .id(imagem.getId())
                .url(imagem.getUrl())
                .descricao(imagem.getDescricao())
                .nomeArquivoOriginal(imagem.getNomeArquivoOriginal())
                .build();
    }

    public void updateContratoData(Contrato contrato, ContratoRequestDTO dto) {

        if (dto.getCliente() != null) {
            Cliente cliente = clienteRepository.findById(dto.getCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            contrato.setCliente(cliente);
        }
        if (dto.getValorRecebido() != null) {
            contrato.setValorRecebido(dto.getValorRecebido());
        }
        if (dto.getValorPendente() != null) {
            contrato.setValorPendente(dto.getValorPendente());
        }
        if (dto.getValorTotal() != null) {
            contrato.setValorTotal(dto.getValorTotal());
        }
        if (dto.getTipoDoContrato() != null) {
            contrato.setTipoDoContrato(dto.getTipoDoContrato());
        }
        if (dto.getDataHoraInicial() != null) {
            contrato.setDataHoraInicial(dto.getDataHoraInicial());
        }
        if (dto.getDataHoraFinal() != null) {
            contrato.setDataHoraFinal(dto.getDataHoraFinal());
        }
        if (dto.getDuracao() != null) {
            contrato.setDuracao(dto.getDuracao());
        }
        if (dto.getQuantidadeConvidados() != null) {
            contrato.setQuantidadeConvidados(dto.getQuantidadeConvidados());
        }
        //Não posso mudar a situação aqui
        //if (dto.getSituacao() != null) {
        //    contrato.setSituacao(dto.getSituacao());
        //}
        if (dto.getObservacoes() != null) {
            contrato.setObservacoes(dto.getObservacoes());
        }
        if (dto.getDesconto() != null) {
            contrato.setDesconto(dto.getDesconto());
        }
        if (dto.getAcrescimo() != null) {
            contrato.setAcrescimo(dto.getAcrescimo());
        }
        if (dto.getItensContrato() != null) {
            List<ItemContrato> itensContrato = itemContratoRepository.findAllById(dto.getItensContrato());
            contrato.setItensContrato(itensContrato);
        }
        if (dto.getTemas() != null) {
            List<Tema> temas = temaRepository.findAllById(dto.getTemas());
            contrato.setTemas(temas);
        }
        if (dto.getAniversariantes() != null) {
            // IDs dos aniversariantes enviados no request (se houver ID nos DTOs)
            List<Long> novosIds = dto.getAniversariantes().stream()
                    .map(AniversarianteRequestDTO::getId) // Certifique-se de que AniversarianteRequestDTO possui 'id'.
                    .filter(id -> id != null) // Evita IDs nulos
                    .toList();

            // Remover aniversariantes que não estão mais no DTO
            contrato.getAniversariantes().removeIf(aniversariante ->
                    !novosIds.contains(aniversariante.getId()) // Remove itens não presentes no request
            );

            // Adicionar ou atualizar aniversariantes
            for (AniversarianteRequestDTO aniversarianteDTO : dto.getAniversariantes()) {
                Aniversariante aniversariante = contrato.getAniversariantes().stream()
                        .filter(a -> a.getId() != null && a.getId().equals(aniversarianteDTO.getId())) // Busca pelo ID
                        .findFirst()
                        .orElseGet(() -> {
                            // Se não encontrou, cria um novo aniversariante
                            Aniversariante novoAniversariante = new Aniversariante();
                            novoAniversariante.setContrato(contrato); // Relaciona com o contrato
                            contrato.getAniversariantes().add(novoAniversariante);
                            return novoAniversariante;
                        });

                // Atualiza as propriedades do aniversariante
                aniversariante.setNomeAniversariante(aniversarianteDTO.getNomeAniversariante());
                aniversariante.setIdade(aniversarianteDTO.getIdade());
                aniversariante.setIdadeNoEvento(aniversarianteDTO.getIdadeNoEvento());
            }
        }
    }

}

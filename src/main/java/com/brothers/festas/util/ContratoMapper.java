package com.brothers.festas.util;

import com.brothers.festas.dto.request.ContratoRequestDTO;
import com.brothers.festas.dto.response.AniversarianteResponseDTO;
import com.brothers.festas.dto.response.ContratoCalendarioResponseDTO;
import com.brothers.festas.dto.response.ContratoResponseDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;
import com.brothers.festas.dto.response.PagamentoResponseDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import com.brothers.festas.model.Aniversariante;
import com.brothers.festas.model.Cliente;
import com.brothers.festas.model.Contrato;
import com.brothers.festas.model.ItemContrato;
import com.brothers.festas.model.Pagamento;
import com.brothers.festas.model.Tema;
import com.brothers.festas.repository.AniversarianteRepository;
import com.brothers.festas.repository.ClienteRepository;
import com.brothers.festas.repository.ItemContratoRepository;
import com.brothers.festas.repository.TemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContratoMapper {

    private final TemaRepository temaRepository;
    private final ClienteRepository clienteRepository;
    private final AniversarianteRepository aniversarianteRepository;
    private final ItemContratoRepository itemContratoRepository;

    public Contrato toEntity(ContratoRequestDTO dto) {
        Contrato contrato = new Contrato();

        contrato.setValorRecebido(dto.getValorRecebido());
        contrato.setValorPendente(dto.getValorPendente());
        contrato.setValorTotal(dto.getValorTotal());

        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        contrato.setCliente(cliente);

        contrato.setCliente(cliente);
        contrato.setTipoDoContrato(dto.getTipoDoContrato());
        contrato.setDataHoraInicial(dto.getDataHoraInicial());
        contrato.setDataHoraFinal(dto.getDataHoraFinal());
        contrato.setDuracao(dto.getDuracao());
        contrato.setQuantidadeConvidados(dto.getQuantidadeConvidados());
        contrato.setObservacoes(dto.getObservacoes());
        contrato.setDesconto(dto.getDesconto());
        contrato.setAcrescimo(dto.getAcrescimo());

        List<Aniversariante> aniversariantes = aniversarianteRepository.findAllById(dto.getListaAniversariantes());
        contrato.setListaAniversariantes(aniversariantes);

        List<ItemContrato> itensContrato = itemContratoRepository.findAllById(dto.getItensContrato());
        contrato.setItensContrato(itensContrato);

        return contrato;
    }

    public ContratoResponseDTO toResponse(Contrato contrato) {
        return ContratoResponseDTO.builder()
                .id(contrato.getId())
                .cliente(contrato.getCliente() != null ? contrato.getCliente().getId() : null)
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
                .itensContrato(contrato.getItensContrato() != null ? contrato.getItensContrato().stream()
                        .map(this::toItemContratoResponseDTO).toList() : List.of())
                .listaAniversariantes(contrato.getListaAniversariantes() != null ? contrato.getListaAniversariantes().stream()
                        .map(this::toAniversarianteResponseDTO).toList() : List.of())
                .pagamentos(contrato.getPagamentos() != null ? contrato.getPagamentos().stream()
                        .map(this::toPagamentoResponseDTO).toList() : List.of())
                .build();
    }

    public ContratoCalendarioResponseDTO toResponseCalendario(Contrato contrato) {
        return ContratoCalendarioResponseDTO.builder()
                .id(contrato.getId())
                .nomeCliente(contrato.getCliente() != null ? contrato.getCliente().getNome() : null)
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
                .nome(aniversariante.getNome())
                .dataNascimento(aniversariante.getDataNascimento())
                .idade(aniversariante.getIdade())
                .idadeNoEvento(aniversariante.getIdadeNoEvento())
                .tema(aniversariante.getTemas().stream()
                        .map(TemaResponseDTO::new)
                        .collect(Collectors.toList()))
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
}

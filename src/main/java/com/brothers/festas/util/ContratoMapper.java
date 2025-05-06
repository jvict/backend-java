package com.brothers.festas.util;

import com.brothers.festas.dto.request.ContratoRequestDTO;
import com.brothers.festas.dto.response.ContratoResponseDTO;
import com.brothers.festas.model.Aniversariante;
import com.brothers.festas.model.Cliente;
import com.brothers.festas.model.Contrato;
import com.brothers.festas.model.ItemContrato;
import com.brothers.festas.model.Tema;
import com.brothers.festas.repository.AniversarianteRepository;
import com.brothers.festas.repository.ClienteRepository;
import com.brothers.festas.repository.ItemContratoRepository;
import com.brothers.festas.repository.TemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

        List<Tema> temas = temaRepository.findAllById(dto.getTemas());
        contrato.setTemas(temas);

        return contrato;
    }

    public ContratoResponseDTO toResponse(Contrato contrato) {
        ContratoResponseDTO dto = new ContratoResponseDTO();

        dto.setId(contrato.getId());
        dto.setValorRecebido(contrato.getValorRecebido());
        dto.setValorPendente(contrato.getValorPendente());
        dto.setValorTotal(contrato.getValorTotal());
        dto.setCliente(contrato.getCliente().getId());
        dto.setTipoDoContrato(contrato.getTipoDoContrato());
        dto.setDataHoraInicial(contrato.getDataHoraInicial());
        dto.setDataHoraFinal(contrato.getDataHoraFinal());
        dto.setDuracao(contrato.getDuracao());
        dto.setQuantidadeConvidados(contrato.getQuantidadeConvidados());
        dto.setObservacoes(contrato.getObservacoes());
        dto.setDesconto(contrato.getDesconto());
        dto.setAcrescimo(contrato.getAcrescimo());

        /*
        // aniversariantes (Clientes)
        List<AniversarianteResponseDTO> aniversariantes = contrato.getListaAniversariantes().stream()
                .map(cli -> {
                    AniversarianteResponseDTO ar = new AniversarianteResponseDTO();
                    ar.setName(cli.getNome());
                    ar.setDate(cli.getDataNascimento().toString());
                    ar.setTema(cli.getTema());
                    ar.setAge(cli.getIdade());
                    ar.setAgeAtEvent(cli.getIdadeNoEvento());
                    return ar;
                }).collect(Collectors.toList());
        dto.setListaAniversariantes(aniversariantes);



        // itens do contrato
        List<ItemContratoResponseDTO> itensDto = contrato.getItemContrato().stream()
                .map(item -> {
                    ItemContratoResponseDTO it = new ItemContratoResponseDTO(item.getDescricao(), item.getValor());
                    return it;
                }).collect(Collectors.toList());
        dto.setItemContrato(itensDto);
        */


        /*
        // pagamentos
        List<PagamentoResponseDTO> pagamentosDto = contrato.getPagamentos().stream()
                .map(p -> {
                    PagamentoResponseDTO pr = new PagamentoResponseDTO();
                    pr.setId(p.getId());
                    pr.setValor(p.getValor());
                    pr.setMeioPagamento(p.getMeioPagamento());
                    pr.setDataPagamentos(p.getDataPagamentos());
                    pr.setRecebido(p.getRecebido());
                    pr.setObservacoes(p.getObservacoes());
                    return pr;
                }).collect(Collectors.toList());
        dto.setPayments(pagamentosDto);

         */

        return dto;
    }
}

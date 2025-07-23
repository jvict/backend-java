package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.PagamentoRequestDTO;
import com.brothers.festas.dto.response.PagamentoResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Contrato;
import com.brothers.festas.model.Pagamento;
import com.brothers.festas.repository.ContratoRepository;
import com.brothers.festas.repository.PagamentoRepository;
import com.brothers.festas.service.IPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PagamentoServiceImpl implements IPagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public List<PagamentoResponseDTO> criarPagamento(Long idContrato, List<PagamentoRequestDTO> requestList) throws ServiceException {
        Optional<Contrato> contratoOpt = contratoRepository.findById(idContrato);
        if (contratoOpt.isEmpty()){
            throw new ServiceException("Contrato não encontrado para inserção/atualização do pagamento");
        }

        if (requestList.isEmpty()){
            return Collections.emptyList();
        }

        Contrato contrato = contratoOpt.get();
        List<PagamentoResponseDTO> responseList = new ArrayList<>();

        // Acompanhe os IDs da lista de requisições para identificar pagamentos a serem potencialmente excluídos
        Set<Long> requestPaymentIds = requestList.stream()
                .map(PagamentoRequestDTO::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // Obtenha os pagamentos existentes para o contrato
        List<Pagamento> existingPayments = pagamentoRepository.findByContrato(contrato); // Você precisaria deste novo método no seu repositório

        // Processar atualizações e inserções
        for (PagamentoRequestDTO request : requestList) {
            Pagamento pagamento;
            if (request.getId() != null) {
                // Tentar encontrar um pagamento existente para atualização
                Optional<Pagamento> existingPagamentoOpt = existingPayments.stream()
                        .filter(p -> p.getId().equals(request.getId()))
                        .findFirst();

                if (existingPagamentoOpt.isPresent()) {
                    pagamento = existingPagamentoOpt.get();
                    // Atualizar os campos do pagamento existente
                    pagamento.setValor(request.getValor());
                    pagamento.setMeioPagamento(request.getMeioPagamento());
                    pagamento.setDataPagamento(request.getDataPagamento());
                    pagamento.setRecebido(request.getRecebido());
                    pagamento.setObservacoes(request.getObservacoes());
                    // contrato já está definido
                } else {
                    throw new ServiceException("Pagamento com ID " + request.getId() + " não encontrado para o contrato " + idContrato);
                }
            } else {
                // Novo pagamento
                pagamento = new Pagamento();
                pagamento.setValor(request.getValor());
                pagamento.setMeioPagamento(request.getMeioPagamento());
                pagamento.setDataPagamento(request.getDataPagamento());
                pagamento.setRecebido(request.getRecebido());
                pagamento.setObservacoes(request.getObservacoes());
                pagamento.setContrato(contrato);
            }
            Pagamento saved = pagamentoRepository.save(pagamento);
            responseList.add(new PagamentoResponseDTO(saved));
        }

        // Identificar e excluir pagamentos que não estavam na requestList (se esse for o comportamento desejado)
        // Isso é frequentemente chamado de abordagem de "reconciliação" ou "sincronização".
        existingPayments.stream()
                .filter(p -> !requestPaymentIds.contains(p.getId()))
                .forEach(pagamentoRepository::delete);

        return responseList;
    }

}
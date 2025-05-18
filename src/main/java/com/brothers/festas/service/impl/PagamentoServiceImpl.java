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

import java.util.List;
import java.util.Optional;
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
            throw new ServiceException("Contrato não encontrado para inserção do pagamento");
        }

        if (requestList.isEmpty()){
            throw new ServiceException("A lista de pagamentos está vazia");
        }

        Contrato contrato = contratoOpt.get();

        return requestList.stream()
                .map(request -> {
                    Pagamento pagamento = new Pagamento();
                    pagamento.setValor(request.getValor());
                    pagamento.setMeioPagamento(request.getMeioPagamento());
                    pagamento.setDataPagamento(request.getDataPagamento());
                    pagamento.setRecebido(request.getRecebido());
                    pagamento.setObservacoes(request.getObservacoes());
                    pagamento.setContrato(contrato);

                    Pagamento saved = pagamentoRepository.save(pagamento);
                    return new PagamentoResponseDTO(saved); // ou construa manualmente
                })
                .collect(Collectors.toList());

    }

}
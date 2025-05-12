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

import java.util.Optional;

@Service
public class PagamentoServiceImpl implements IPagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public PagamentoResponseDTO criarPagamento(Long idContrato, PagamentoRequestDTO request) throws ServiceException {
        Optional<Contrato> contrato = contratoRepository.findById(idContrato);
        if (contrato.isEmpty()){
            throw new ServiceException("Contrato não encontrado para inserção do pagamento");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(request.getValor());
        pagamento.setMeioPagamento(request.getMeioPagamento());
        pagamento.setDataPagamento(request.getDataPagamento());
        pagamento.setRecebido(request.getRecebido());
        pagamento.setObservacoes(request.getObservacoes());
        pagamento.setContrato(contrato.get());

        return new PagamentoResponseDTO(pagamentoRepository.save(pagamento));
    }

}
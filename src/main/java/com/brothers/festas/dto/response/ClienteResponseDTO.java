package com.brothers.festas.dto.response;

import com.brothers.festas.model.Cliente;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String celular;
    private String email;
    private LocalDateTime dataCadastro;
    private String documento;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private boolean status;

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.celular = cliente.getCelular();
        this.email = cliente.getEmail();
        this.dataCadastro = cliente.getDataCadastro();
        this.documento = cliente.getDocumento();
        this.cep = cliente.getCep();
        this.endereco = cliente.getEndereco();
        this.numero = cliente.getNumero();
        this.complemento = cliente.getDocumento();
        this.bairro = cliente.getBairro();
        this.cidade = cliente.getCidade();
        this.uf = cliente.getUf();
        this.status = cliente.isStatus();

    }
}

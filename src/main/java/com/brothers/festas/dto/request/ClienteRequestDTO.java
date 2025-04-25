package com.brothers.festas.dto.request;


import lombok.Getter;

@Getter
public class ClienteRequestDTO {

    private String nome;
    private String celular;
    private String email;
    private String documento;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private Boolean status;
}

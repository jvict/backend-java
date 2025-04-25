package com.brothers.festas.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioRegistroRequestDTO {
    private String name;
    private String email;
    private String password;
    private List<String> roles;
}

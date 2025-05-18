package com.brothers.festas.controller;

import com.brothers.festas.dto.request.LoginRequestDTO;
import com.brothers.festas.dto.request.UsuarioRegistroRequestDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Usuario;
import com.brothers.festas.service.security.AuthService;
import com.brothers.festas.service.impl.UsuarioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?>  registerUser(@RequestBody UsuarioRegistroRequestDTO registroDTO) throws ServiceException {
        try {
            Usuario usuario = usuarioServiceImpl.cadastrarUsuario(registroDTO);
            return ResponseEntity.ok(usuario);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        String token = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}

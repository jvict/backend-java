package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.UsuarioRegistroRequestDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Role;
import com.brothers.festas.model.Usuario;
import com.brothers.festas.repository.RoleRepository;
import com.brothers.festas.repository.UsuarioRepository;
import com.brothers.festas.service.UsuarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrarUsuario(UsuarioRegistroRequestDTO registroDTO) throws ServiceException{
        try {
            Usuario usuario = new Usuario();

            usuario.setName(registroDTO.getName());
            usuario.setEmail(registroDTO.getEmail());
            usuario.setActive(true);
            usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

            Set<Role> roles = registroDTO.getRoles().stream()
                    .map(roleName -> roleRepository.findByNome(roleName)
                            .orElseThrow(() -> new ServiceException("Role não encontrada: " + roleName)))
                    .collect(Collectors.toSet());

            if (!roles.isEmpty()) {
                usuario.setRoles(roles);
            }

            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Usuário já existe.");
        }
    }
}

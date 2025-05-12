package com.brothers.festas.service;

import com.brothers.festas.dto.request.UsuarioRegistroRequestDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Usuario;

public interface IUsuarioService {

    Usuario cadastrarUsuario(UsuarioRegistroRequestDTO registroDTO) throws ServiceException;
}

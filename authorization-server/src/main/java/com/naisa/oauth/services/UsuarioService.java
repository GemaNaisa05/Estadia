package com.naisa.oauth.services;

import java.util.Set;

import com.naisa.oauth.dto.UsuarioRequest;
import com.naisa.oauth.dto.UsuarioResponse;

public interface UsuarioService {
	Set<UsuarioResponse> listarUsuarios();
	
	UsuarioResponse crearUsuario(UsuarioRequest request);
	
	UsuarioResponse eliminarUsuario(String username);

}

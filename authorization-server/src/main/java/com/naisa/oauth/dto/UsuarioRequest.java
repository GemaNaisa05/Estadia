package com.naisa.oauth.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
		@NotBlank (message = "El username es requerido")
		@Size (min = 5 , max =20,message = "El username debe contener 5 y 20 caracteres" )
		String username,
		@NotBlank (message = "El password es requerido")
		@Size (min = 8 , message = "El password debe contener 8 caracteres" )
		String password,
		@NotBlank (message = "los es requerido")
		@Size (min = 1 , message = "El usuario debe contener 1 rol" )
		Set<String> roles
		
		
		
		
		
		) {

}

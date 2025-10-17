package com.naisa.commons.dto;

import jakarta.validation.constraints.*;

public record HuespedesRequest(
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 10, max = 50, message = "El nombre debe tener al menos 10 caracteres")
    String nombre,
    
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 10, max = 50, message = "El apellido debe tener al menos 10 caracteres")
    String apellido,
    
    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email es obligatorio")
    String email,
    
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe tener exactamente 10 dígitos numéricos")
    String telefono,
    
    @NotBlank(message = "El documento es obligatorio")
    String documento,
    
    @NotBlank(message = "La nacionalidad es obligatoria")
    String nacionalidad
) {}
package com.naisa.commons.dto;

import java.time.LocalDateTime;

public record HuespedesResponse(
    Long id,
    String nombre,
    String apellido,
    String email,
    String telefono,
    String documento,
    String nacionalidad,
    LocalDateTime fechaRegistro,
    Boolean activo
) {}
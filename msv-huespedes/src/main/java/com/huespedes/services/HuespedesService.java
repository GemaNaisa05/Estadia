package com.huespedes.services;

import com.huespedes.models.Huespedes;
import java.util.List;
import java.util.Optional;

public interface HuespedesService {
    List<Huespedes> findAll();
    Optional<Huespedes> findById(Long id);
    Optional<Huespedes> findByEmail(String email);
    Optional<Huespedes> findByDocumentoIdentidad(String documentoIdentidad);
    List<Huespedes> findByNombre(String nombre);
    List<Huespedes> findByApellido(String apellido);
    List<Huespedes> findByNacionalidad(String nacionalidad);
    Huespedes save(Huespedes huespedes);
    Huespedes update(Long id, Huespedes huespedesDetails);
    void deleteById(Long id);
    boolean existsById(Long id);
}
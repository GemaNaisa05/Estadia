package com.huespedes.repositories;

import com.huespedes.models.Huespedes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuespedesRepository extends JpaRepository<Huespedes, Long> {
    
    Optional<Huespedes> findByEmail(String email);
    Optional<Huespedes> findByDocumentoIdentidad(String documentoIdentidad);
    
    boolean existsByEmail(String email);
    boolean existsByDocumentoIdentidad(String documentoIdentidad);
    
    List<Huespedes> findByNombreContainingIgnoreCase(String nombre);
    List<Huespedes> findByApellidoContainingIgnoreCase(String apellido);
    
    @Query("SELECT h FROM Huespedes h WHERE LOWER(h.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND LOWER(h.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))")
    List<Huespedes> findByNombreAndApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);
    
    List<Huespedes> findByNacionalidadContainingIgnoreCase(String nacionalidad);
}
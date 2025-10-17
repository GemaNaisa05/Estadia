package com.huespedes.services;

import com.huespedes.models.Huespedes;
import com.huespedes.repositories.HuespedesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuespedesServiceImpl implements HuespedesService {
    
    @Autowired
    private HuespedesRepository huespedesRepository;
    
    @Override
    public List<Huespedes> findAll() {
        return huespedesRepository.findAll();
    }
    
    @Override
    public Optional<Huespedes> findById(Long id) {
        return huespedesRepository.findById(id);
    }
    
    @Override
    public Optional<Huespedes> findByEmail(String email) {
        return huespedesRepository.findByEmail(email);
    }
    
    @Override
    public Optional<Huespedes> findByDocumentoIdentidad(String documentoIdentidad) {
        return huespedesRepository.findByDocumentoIdentidad(documentoIdentidad);
    }
    
    @Override
    public List<Huespedes> findByNombre(String nombre) {
        return huespedesRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    @Override
    public List<Huespedes> findByApellido(String apellido) {
        return huespedesRepository.findByApellidoContainingIgnoreCase(apellido);
    }
    
    @Override
    public List<Huespedes> findByNacionalidad(String nacionalidad) {
        return huespedesRepository.findByNacionalidadContainingIgnoreCase(nacionalidad);
    }
    
    @Override
    public Huespedes save(Huespedes huespedes) {
        // Validar que el email no exista
        if (huespedesRepository.existsByEmail(huespedes.getEmail())) {
            throw new RuntimeException("Ya existe un huésped registrado con el email: " + huespedes.getEmail());
        }
        
        // Validar que el documento no exista
        if (huespedesRepository.existsByDocumentoIdentidad(huespedes.getDocumentoIdentidad())) {
            throw new RuntimeException("Ya existe un huésped registrado con el documento: " + huespedes.getDocumentoIdentidad());
        }
        
        return huespedesRepository.save(huespedes);
    }
    
    @Override
    public Huespedes update(Long id, Huespedes huespedesDetails) {
        Huespedes huespedesExistente = huespedesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Huésped no encontrado con ID: " + id));
        
        // 🔥 CORRECCIÓN: Solo validar email si cambió Y si ya existe en OTRO huésped
        if (!huespedesExistente.getEmail().equals(huespedesDetails.getEmail())) {
            Optional<Huespedes> huespedConEmail = huespedesRepository.findByEmail(huespedesDetails.getEmail());
            if (huespedConEmail.isPresent() && !huespedConEmail.get().getId().equals(id)) {
                throw new RuntimeException("Ya existe otro huésped registrado con el email: " + huespedesDetails.getEmail());
            }
        }
        
        // 🔥 CORRECCIÓN: Solo validar documento si cambió Y si ya existe en OTRO huésped
        if (!huespedesExistente.getDocumentoIdentidad().equals(huespedesDetails.getDocumentoIdentidad())) {
            Optional<Huespedes> huespedConDocumento = huespedesRepository.findByDocumentoIdentidad(huespedesDetails.getDocumentoIdentidad());
            if (huespedConDocumento.isPresent() && !huespedConDocumento.get().getId().equals(id)) {
                throw new RuntimeException("Ya existe otro huésped registrado con el documento: " + huespedesDetails.getDocumentoIdentidad());
            }
        }
        
        // Actualizar campos
        huespedesExistente.setNombre(huespedesDetails.getNombre());
        huespedesExistente.setApellido(huespedesDetails.getApellido());
        huespedesExistente.setEmail(huespedesDetails.getEmail());
        huespedesExistente.setTelefono(huespedesDetails.getTelefono());
        huespedesExistente.setDocumentoIdentidad(huespedesDetails.getDocumentoIdentidad());
        huespedesExistente.setNacionalidad(huespedesDetails.getNacionalidad());
        
        return huespedesRepository.save(huespedesExistente);
    }
    
    @Override
    public void deleteById(Long id) {
        if (!huespedesRepository.existsById(id)) {
            throw new RuntimeException("Huésped no encontrado con ID: " + id);
        }
        huespedesRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return huespedesRepository.existsById(id);
    }
}
package com.naisa.tipo.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naisa.commons.dto.TipoRequest;
import com.naisa.commons.dto.TipoResponse;
import com.naisa.commons.exceptions.EntidadRelacionadaException;
import com.naisa.tipo.Clients.MovimientoClient;
import com.naisa.tipo.mappers.TipoMapper;
import com.naisa.tipo.models.Tipo;
import com.naisa.tipo.repositories.TipoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class TipoServiceImpl implements TipoService {

    private final TipoRepository tipoRepository;
    private final TipoMapper tipoMapper;
    private final MovimientoClient movimientoClient;

    @Override
    @Transactional(readOnly = true)
    public List<TipoResponse> listar() {
        log.info("Listado de todos los tipos solicitados");
        return tipoRepository.findAll()
                .stream()
                .map(tipoMapper::entityToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoResponse obtenerPorId(Long id) {
        log.info("Buscando tipo con id {}", id);
        Tipo tipo = getTipoOrThrow(id);
        return tipoMapper.entityToResponse(tipo);
    }

    @Override
    public TipoResponse insertar(TipoRequest request) {
        log.info("Insertando un nuevo tipo con nombre {}", request.nombre());
        return tipoMapper.entityToResponse(
                tipoRepository.save(tipoMapper.requestToEntity(request))
        );
    }

    @Override
    public TipoResponse actualizar(TipoRequest request, Long id) {
        log.info("Buscando tipo con id {}", id);
        Tipo tipo = getTipoOrThrow(id);
        log.info("Actualizando tipo con id {}", id);
        tipo.setNombre(request.nombre());
        return tipoMapper.entityToResponse(tipoRepository.save(tipo));
    }

    @Override
    public void eliminar(Long id) {
        log.info("Validando eliminación de tipo con id {}", id);
        Tipo tipo = getTipoOrThrow(id);
        
        // ok es la VALIDACIÓN CON EXCEPCIÓN PERSONALIZADA
        if (movimientoClient.tipoPresente(id)) {
            throw new EntidadRelacionadaException("No se puede eliminar el tipo porque tiene datos asociados en el sistema");
        }
        
        tipoRepository.delete(tipo);
        log.info("Tipo eliminado: {}", tipo.getNombre());
    }

    private Tipo getTipoOrThrow(Long id) {
        return tipoRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Tipo no encontrado con el id: " + id));
    }
}
package com.naisa.tipo.mappers;

import org.springframework.stereotype.Component;

import com.naisa.commons.dto.TipoRequest;
import com.naisa.commons.dto.TipoResponse;
import com.naisa.commons.mappers.CommonMapper;
import com.naisa.tipo.models.Tipo;



@Component
public class TipoMapper extends CommonMapper<TipoRequest, TipoResponse, Tipo>{

	@Override
	public TipoResponse entityToResponse(Tipo entity) {
		if (entity == null) {
			return null;
		}
		return new TipoResponse(
				entity.getId(),
				entity.getNombre()
		);
	}

	@Override
	public Tipo requestToEntity(TipoRequest request) {
		if (request == null) {
			return null;
		}
		Tipo tipo = new Tipo();
		tipo.setNombre(request.nombre());
		return tipo;
	}
	}
	
	
	
	
	
	
	



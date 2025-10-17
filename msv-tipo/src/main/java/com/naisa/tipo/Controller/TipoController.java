package com.naisa.tipo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.naisa.commons.controllers.CommonController;
import com.naisa.commons.dto.TipoRequest;
import com.naisa.commons.dto.TipoResponse;
import com.naisa.tipo.services.TipoService;

@RestController
public class TipoController extends CommonController<TipoRequest, TipoResponse, TipoService>{
	
	public TipoController(TipoService service) {
        super(service);
    }

}

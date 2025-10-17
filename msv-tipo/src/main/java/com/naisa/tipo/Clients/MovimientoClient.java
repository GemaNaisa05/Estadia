package com.naisa.tipo.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msv-movimiento")
public interface MovimientoClient {
    
    @GetMapping("/id-tipo/{id}")
    boolean tipoPresente(@PathVariable Long id);
}
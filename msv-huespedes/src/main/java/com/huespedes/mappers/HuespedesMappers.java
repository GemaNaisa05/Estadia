package com.huespedes.mappers;

import com.huespedes.models.Huespedes;
import org.springframework.stereotype.Component;

@Component
public class HuespedesMappers {
    
    public Huespedes copyHuespedes(Huespedes source, Huespedes target) {
        if (source == null || target == null) {
            return target;
        }
        
        target.setNombre(source.getNombre());
        target.setApellido(source.getApellido());
        target.setEmail(source.getEmail());
        target.setTelefono(source.getTelefono());
        
        
        return target;
    }
    
    public Huespedes createHuespedesFromData(String nombre, String apellido, String email, String telefono, String direccion) {
        Huespedes huespedes = new Huespedes();
        huespedes.setNombre(nombre);
        huespedes.setApellido(apellido);
        huespedes.setEmail(email);
        huespedes.setTelefono(telefono);
        
        return huespedes;
    }
}
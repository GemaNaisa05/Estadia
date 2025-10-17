package com.huespedes.controller;

import com.huespedes.models.Huespedes;
import com.huespedes.services.HuespedesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/huespedes")
@CrossOrigin(origins = "*")
public class HuespedesController {
    
    @Autowired
    private HuespedesService huespedesService;
    
    // HEALTH CHECK
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Microservicio de Huéspedes con validaciones - " + System.currentTimeMillis());
    }
    
    // GET - Obtener todos los huéspedes
    @GetMapping
    public ResponseEntity<List<Huespedes>> getAllHuespedes() {
        try {
            List<Huespedes> huespedes = huespedesService.findAll();
            return ResponseEntity.ok(huespedes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // GET - Obtener huésped por ID
    @GetMapping("/{id}")
    public ResponseEntity<Huespedes> getHuespedesById(@PathVariable Long id) {
        try {
            Optional<Huespedes> huespedes = huespedesService.findById(id);
            return huespedes.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // GET - Obtener huésped por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Huespedes> getHuespedesByEmail(@PathVariable String email) {
        try {
            Optional<Huespedes> huespedes = huespedesService.findByEmail(email);
            return huespedes.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // GET - Obtener huésped por documento
    @GetMapping("/documento/{documento}")
    public ResponseEntity<Huespedes> getHuespedesByDocumento(@PathVariable String documento) {
        try {
            Optional<Huespedes> huespedes = huespedesService.findByDocumentoIdentidad(documento);
            return huespedes.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // GET - Buscar huéspedes por nombre
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<Huespedes>> getHuespedesByNombre(@PathVariable String nombre) {
        try {
            List<Huespedes> huespedes = huespedesService.findByNombre(nombre);
            if (huespedes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(huespedes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // GET - Buscar huéspedes por apellido
    @GetMapping("/buscar/apellido/{apellido}")
    public ResponseEntity<List<Huespedes>> getHuespedesByApellido(@PathVariable String apellido) {
        try {
            List<Huespedes> huespedes = huespedesService.findByApellido(apellido);
            if (huespedes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(huespedes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // GET - Buscar huéspedes por nacionalidad
    @GetMapping("/buscar/nacionalidad/{nacionalidad}")
    public ResponseEntity<List<Huespedes>> getHuespedesByNacionalidad(@PathVariable String nacionalidad) {
        try {
            List<Huespedes> huespedes = huespedesService.findByNacionalidad(nacionalidad);
            if (huespedes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(huespedes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // POST - Crear nuevo huésped CON VALIDACIONES
    @PostMapping
    public ResponseEntity<?> createHuespedes(@Valid @RequestBody Huespedes huespedes, BindingResult result) {
        try {
            // Validar errores de validación
            if (result.hasErrors()) {
                Map<String, String> errores = new HashMap<>();
                result.getFieldErrors().forEach(error -> {
                    errores.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.badRequest().body(errores);
            }
            
            Huespedes nuevoHuespedes = huespedesService.save(huespedes);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHuespedes);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor al crear el huésped");
        }
    }
    
    // PUT - Actualizar huésped existente CON VALIDACIONES
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHuespedes(@PathVariable Long id, @Valid @RequestBody Huespedes huespedesDetails, BindingResult result) {
        try {
            // Validar errores de validación
            if (result.hasErrors()) {
                Map<String, String> errores = new HashMap<>();
                result.getFieldErrors().forEach(error -> {
                    errores.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.badRequest().body(errores);
            }
            
            Huespedes huespedesActualizado = huespedesService.update(id, huespedesDetails);
            return ResponseEntity.ok(huespedesActualizado);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor al actualizar el huésped");
        }
    }
    
    // DELETE - Eliminar huésped
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHuespedes(@PathVariable Long id) {
        try {
            huespedesService.deleteById(id);
            return ResponseEntity.ok().body("Huésped eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor al eliminar el huésped");
        }
    }
}
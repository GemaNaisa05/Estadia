package com.huespedes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "huespedes")
public class Huespedes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "huesped_seq")
    @SequenceGenerator(name = "huesped_seq", sequenceName = "huesped_seq", allocationSize = 1)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 10, max = 50, message = "El nombre debe tener al menos 10 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 10, max = 50, message = "El apellido debe tener al menos 10 caracteres")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    
    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 10, max = 10, message = "El teléfono debe tener 10 caracteres")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe tener exactamente 10 dígitos numéricos")
    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;
    
    @NotBlank(message = "El documento de identidad es obligatorio")
    @Size(min = 5, max = 20, message = "El documento debe tener entre 5 y 20 caracteres")
    @Column(name = "documento_identidad", nullable = false, length = 20)
    private String documentoIdentidad;
    
    @NotBlank(message = "La nacionalidad es obligatoria")
    @Size(min = 3, max = 50, message = "La nacionalidad debe tener entre 3 y 50 caracteres")
    @Column(name = "nacionalidad", nullable = false, length = 50)
    private String nacionalidad;
    
  
    
    // Constructores
    public Huespedes() {}
    
    public Huespedes(String nombre, String apellido, String email, String telefono, 
                    String documentoIdentidad, String nacionalidad, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.documentoIdentidad = documentoIdentidad;
        this.nacionalidad = nacionalidad;
        
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }
    
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    
    
    @Override
    public String toString() {
        return "Huespedes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
package com.naisa.tipo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TIPO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO")
    @SequenceGenerator(name = "SEQ_TIPO", sequenceName = "SEQ_TIPO", allocationSize = 1)
    @Column(name = "ID_TIPO")
    private Long id;

    @NotBlank(message = "El nombre del tipo es requerido")
    @Size(min = 1, max = 30, message = "El nombre debe tener entre 1 y 30 caracteres")
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
}

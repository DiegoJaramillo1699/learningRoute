package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
//@AllArgsConstructor
public class ClienteDTO {

    private Long id;

    private String nombres;

    private String apellidos;

    private String telefono;

    private String documento;

    private String tipo;

    private Long edad;

    private String ciudadNacimiento;

    private String imagen;
}

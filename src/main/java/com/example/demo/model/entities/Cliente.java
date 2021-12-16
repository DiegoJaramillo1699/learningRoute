package com.example.demo.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CLIENTE")
@Table(indexes = @Index(name = "cliente_documento_index", columnList = "DOCUMENTO"))
//@JsonIgnoreProperties(value= {"facturas","visitas"})
public class Cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "CLIENTE_ID")
        private Long id;

        @Size(min = 3,message = "El nombre debe contener mínimo 3 caracteres")
        @NotEmpty(message = "El nombre no puede estar vacío")
        @NotBlank(message = "El nombre no puede estar vacío")
        @Column(name = "NOMBRES")
        private String nombres;

        @Size(min = 3,message = "El apellido debe contener mínimo 3 caracteres")
        @NotEmpty(message = "El apellido no puede estar vacío")
        @NotBlank(message = "El apellido no puede estar vacío")
        @Column(name = "APELLIDOS")
        private String apellidos;


        @Column(name = "TELEFONO")
        private String telefono;

        @NotEmpty(message = "El documento no puede estar vacío")
        @NotBlank(message = "El documento no puede estar vacío")
        @Column(name = "DOCUMENTO",unique = true)
        private String documento;

        @Column(name = "TIPO")
        private String tipo;

        @Column(name = "EDAD")
        private Long edad;

        @Column(name = "CIUDAD_NACIMIENTO")
        private String ciudadNacimiento;

        /*@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Factura> facturas;

        @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Visita> visitas;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "UBICACION_ID", referencedColumnName = "UBICACION_ID")
        private Ubicacion ubicacion; */
}

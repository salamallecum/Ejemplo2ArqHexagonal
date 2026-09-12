package com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Clase que define el objeto domain estudiante
public class Estudiante {

    private Long id;
    private String nombres;
    private String apellidos;
    private String username;
    private String password;
    private String telefono;
    private String edad;
    private String direccion;

}

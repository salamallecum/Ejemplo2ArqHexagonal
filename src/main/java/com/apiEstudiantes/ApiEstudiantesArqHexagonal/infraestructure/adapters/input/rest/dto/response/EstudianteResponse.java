package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Clase Dto encargada de definir el objeto que da respuesta a las peticiones http de los estudiantes
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteResponse {

    private Long id;
    private String nombres;
    private String apellidos;
    private String username;
    private String password;
    private String telefono;
    private String edad;
    private String direccion;
}

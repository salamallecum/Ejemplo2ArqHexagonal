package com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

//Clase que define la estructura de la respuesta de error que se enviará al cliente en caso de que ocurra un error en la API REST
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String codigoError;
    private String mensaje;
    private List<String> detalles;
    private LocalDateTime timestamp;
}

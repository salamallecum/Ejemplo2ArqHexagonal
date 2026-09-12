package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

//Clase que define los códigos de error que se pueden presentar en la aplicación
@Getter
@AllArgsConstructor
public enum ErrorCatalog {

    //Códigos de error definidos para la aplicación, con su respectivo mensaje de error
    ESTUDIANTE_NOT_FOUND("ERR-EST-001", "Estudiante no encontrado."),
    ESTUDIANTE_INVALID("ERR-EST-002", "Datos del estudiante inválidos."),
    GENERIC_ERROR("ERR-SRV-001", "Error interno del servidor.");

    private final String code;
    private final String message;
}

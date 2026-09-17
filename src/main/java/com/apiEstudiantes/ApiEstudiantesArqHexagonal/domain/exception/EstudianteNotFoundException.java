package com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.exception;

//Clase que define la excepción de estudiante no encontrado
public class EstudianteNotFoundException extends RuntimeException{

    public EstudianteNotFoundException(String message){
        super(message);
    }
}

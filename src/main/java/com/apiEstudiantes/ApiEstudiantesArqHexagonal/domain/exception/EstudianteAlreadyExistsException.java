package com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.exception;

//Clase que define la excepción de estudiante ya registrado
public class EstudianteAlreadyExistsException extends RuntimeException{

    public EstudianteAlreadyExistsException(String message){
        super(message);
    }

}

package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.exception.EstudianteAlreadyExistsException;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.exception.EstudianteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.utils.ErrorCatalog.*;

//Clase que define el manejo global de excepciones y respuestas en la aplicación
@RestControllerAdvice
public class GlobalControllerAdvice {

    //Método encargado de definir la excepción que se lanza cuando no se encuentra un estudiante en la base de datos
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EstudianteNotFoundException.class)
    public ErrorResponse handEstudianteNotFoundException() {
        return ErrorResponse.builder()
                .codigoError(ESTUDIANTE_NOT_FOUND.getCode())
                .mensaje(ESTUDIANTE_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Método encargado de definir la excepción que se lanza cuando hay un error en la validación de los datos del estudiante
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        //Obtenemos el BindingResult que contiene los errores de validación
        BindingResult bindingResult = ex.getBindingResult();

        return ErrorResponse.builder()
                .codigoError(ESTUDIANTE_INVALID.getCode())
                .mensaje(ESTUDIANTE_INVALID.getMessage())
                .detalles(bindingResult.getFieldErrors()
                        .stream()
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Método encargado de definir la excepción que se lanza cuando se encuentra un estudiante con el mismo
    // nombre de usuario en la base de datos
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EstudianteAlreadyExistsException.class)
    public ErrorResponse handleEstudianteAlreadyExistsException(EstudianteAlreadyExistsException ex) {
        return ErrorResponse.builder()
                .codigoError(ESTUDIANTE_ALREADY_EXISTS.getCode())
                .mensaje(ESTUDIANTE_ALREADY_EXISTS.getMessage())
                .detalles(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Método encargado de definir la excepción que se lanza cuando ocurre un error genérico en la aplicación
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericException(Exception ex) {
        return ErrorResponse.builder()
                .codigoError("ERR-SRV-001")
                .mensaje(GENERIC_ERROR.getMessage())
                .detalles(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }
}

package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.exception.EstudianteAlreadyExistsException;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.exception.EstudianteNotFoundException;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalControllerAdviceTest {

    private GlobalControllerAdvice advice;

    @BeforeEach
    void setUp() {
        advice = new GlobalControllerAdvice();
    }

    @Test
    void handEstudianteNotFoundException_returnsNotFoundPayload() {
        ErrorResponse result = advice.handEstudianteNotFoundException(
                new EstudianteNotFoundException("Estudiante no encontrado con id: 1"));

        assertEquals("ERR-EST-001", result.getCodigoError());
        assertEquals("Estudiante no encontrado.", result.getMensaje());
        assertEquals(List.of("Estudiante no encontrado con id: 1"), result.getDetalles());
        assertNotNull(result.getTimestamp());
    }

    @Test
    void handMethodArgumentNotValidException_returnsValidationDetails() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("request", "nombres", "no puede estar vacío"),
                new FieldError("request", "edad", "debe ser numérica")));

        ErrorResponse result = advice.handMethodArgumentNotValidException(exception);

        assertEquals("ERR-EST-002", result.getCodigoError());
        assertEquals("Datos del estudiante inválidos.", result.getMensaje());
        assertEquals(List.of(
                "nombres: no puede estar vacío",
                "edad: debe ser numérica"), result.getDetalles());
        assertNotNull(result.getTimestamp());
    }

    @Test
    void handleEstudianteAlreadyExistsException_returnsConflictPayload() {
        ErrorResponse result = advice.handleEstudianteAlreadyExistsException(
                new EstudianteAlreadyExistsException("Ya existe un estudiante con ese username"));

        assertEquals("ERR-EST-003", result.getCodigoError());
        assertEquals("Estudiante ya existe.", result.getMensaje());
        assertEquals(List.of("Ya existe un estudiante con ese username"), result.getDetalles());
        assertNotNull(result.getTimestamp());
    }

    @Test
    void handleGenericException_returnsInternalErrorPayload() {
        ErrorResponse result = advice.handleGenericException(new IllegalStateException("fallo interno"));

        assertEquals("ERR-SRV-001", result.getCodigoError());
        assertEquals("Error interno del servidor.", result.getMensaje());
        assertEquals(List.of("fallo interno"), result.getDetalles());
        assertNotNull(result.getTimestamp());
    }
}

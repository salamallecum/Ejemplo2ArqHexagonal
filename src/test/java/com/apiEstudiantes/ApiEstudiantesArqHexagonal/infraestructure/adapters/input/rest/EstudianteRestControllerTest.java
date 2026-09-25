package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.ports.input.EstudianteServicePort;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.request.SaveEstudianteRequest;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.response.EstudianteResponse;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.mapper.EstudianteRestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EstudianteRestControllerTest {

    private EstudianteServicePort servicePort;
    private EstudianteRestMapper mapper;
    private EstudianteRestController controller;

    @BeforeEach
    void setUp() {
        servicePort = mock(EstudianteServicePort.class);
        mapper = mock(EstudianteRestMapper.class);
        controller = new EstudianteRestController(servicePort, mapper);
    }

    @Test
    void findAllEstudiantes_returnsMappedList() {
        List<Estudiante> students = List.of(estudiante(1L));
        List<EstudianteResponse> expected = List.of(response(1L));
        when(servicePort.findAll()).thenReturn(students);
        when(mapper.toEstudianteResponseList(students)).thenReturn(expected);

        List<EstudianteResponse> result = controller.findAllEstudiantes();

        assertSame(expected, result);
        verify(servicePort).findAll();
        verify(mapper).toEstudianteResponseList(students);
    }

    @Test
    void findEstudianteById_returnsMappedStudent() {
        Estudiante student = estudiante(2L);
        EstudianteResponse expected = response(2L);
        when(servicePort.findById(2L)).thenReturn(student);
        when(mapper.toEstudianteResponse(student)).thenReturn(expected);

        EstudianteResponse result = controller.findEstudianteById(2L);

        assertSame(expected, result);
        verify(servicePort).findById(2L);
        verify(mapper).toEstudianteResponse(student);
    }

    @Test
    void saveEstudiante_mapsRequestAndReturnsCreatedResponse() {
        SaveEstudianteRequest request = request();
        Estudiante student = estudiante(null);
        Estudiante savedStudent = estudiante(3L);
        EstudianteResponse expected = response(3L);
        when(mapper.toEstudiante(request)).thenReturn(student);
        when(servicePort.save(student)).thenReturn(savedStudent);
        when(mapper.toEstudianteResponse(savedStudent)).thenReturn(expected);

        var result = controller.saveEstudiante(request);

        assertEquals(201, result.getStatusCode().value());
        assertSame(expected, result.getBody());
        verify(mapper).toEstudiante(request);
        verify(servicePort).save(student);
        verify(mapper).toEstudianteResponse(savedStudent);
    }

    @Test
    void updateEstudiante_mapsRequestAndReturnsUpdatedResponse() {
        SaveEstudianteRequest request = request();
        Estudiante changes = estudiante(null);
        Estudiante updatedStudent = estudiante(4L);
        EstudianteResponse expected = response(4L);
        when(mapper.toEstudiante(request)).thenReturn(changes);
        when(servicePort.update(4L, changes)).thenReturn(updatedStudent);
        when(mapper.toEstudianteResponse(updatedStudent)).thenReturn(expected);

        EstudianteResponse result = controller.updateEstudiante(4L, request);

        assertSame(expected, result);
        verify(mapper).toEstudiante(request);
        verify(servicePort).update(4L, changes);
        verify(mapper).toEstudianteResponse(updatedStudent);
    }

    @Test
    void deleteEstudiante_deletesStudentAndReturnsAccepted() {
        var result = controller.deleteEstudiante(5L);

        assertEquals(202, result.getStatusCode().value());
        assertEquals(Map.of("Mensaje", "Estudiante eliminado con éxito"), result.getBody());
        verify(servicePort).deleteById(5L);
    }

    private Estudiante estudiante(Long id) {
        return new Estudiante(id, "Nombres", "Apellidos", "usuario", "clave",
                "123456", "20", "Dirección");
    }

    private EstudianteResponse response(Long id) {
        return new EstudianteResponse(id, "Nombres", "Apellidos", "usuario", "clave",
                "123456", "20", "Dirección");
    }

    private SaveEstudianteRequest request() {
        SaveEstudianteRequest request = new SaveEstudianteRequest();
        request.setNombres("Nombres");
        request.setApellidos("Apellidos");
        request.setUsername("usuario");
        request.setPassword("clave");
        request.setTelefono("123456");
        request.setEdad("20");
        request.setDireccion("Dirección");
        return request;
    }
}

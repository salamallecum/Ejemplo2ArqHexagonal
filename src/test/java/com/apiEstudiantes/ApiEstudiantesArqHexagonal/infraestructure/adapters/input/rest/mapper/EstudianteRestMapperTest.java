package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.mapper;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.request.SaveEstudianteRequest;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.response.EstudianteResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteRestMapperTest {

    private final EstudianteRestMapper mapper = Mappers.getMapper(EstudianteRestMapper.class);

    @Test
    void toEstudiante_mapsAllRequestFields() {
        SaveEstudianteRequest request = new SaveEstudianteRequest(
                "Ana", "Pérez", "ana01", "clave", "555123", "20", "Calle 1");

        Estudiante result = mapper.toEstudiante(request);

        assertNotNull(result);
        assertNull(result.getId());
        assertEquals("Ana", result.getNombres());
        assertEquals("Pérez", result.getApellidos());
        assertEquals("ana01", result.getUsername());
        assertEquals("clave", result.getPassword());
        assertEquals("555123", result.getTelefono());
        assertEquals("20", result.getEdad());
        assertEquals("Calle 1", result.getDireccion());
    }

    @Test
    void toEstudianteResponse_mapsAllStudentFields() {
        Estudiante student = estudiante(7L);

        EstudianteResponse result = mapper.toEstudianteResponse(student);

        assertNotNull(result);
        assertEquals(7L, result.getId());
        assertEquals(student.getNombres(), result.getNombres());
        assertEquals(student.getApellidos(), result.getApellidos());
        assertEquals(student.getUsername(), result.getUsername());
        assertEquals(student.getPassword(), result.getPassword());
        assertEquals(student.getTelefono(), result.getTelefono());
        assertEquals(student.getEdad(), result.getEdad());
        assertEquals(student.getDireccion(), result.getDireccion());
    }

    @Test
    void toEstudianteResponseList_mapsEveryStudent() {
        List<Estudiante> students = List.of(estudiante(1L), estudiante(2L));

        List<EstudianteResponse> result = mapper.toEstudianteResponseList(students);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Ana", result.get(0).getNombres());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Ana", result.get(1).getNombres());
    }

    @Test
    void mapperMethods_returnNullForNullInput() {
        assertNull(mapper.toEstudiante(null));
        assertNull(mapper.toEstudianteResponse(null));
        assertNull(mapper.toEstudianteResponseList(null));
    }

    private Estudiante estudiante(Long id) {
        return new Estudiante(id, "Ana", "Pérez", "ana01", "clave",
                "555123", "20", "Calle 1");
    }
}

package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.mapper;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.entity.EstudianteEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstudiantePersistenceMapperTest {

    private final EstudiantePersistenceMapper mapper =
            Mappers.getMapper(EstudiantePersistenceMapper.class);

    @Test
    void toEstudianteEntity_mapsAllStudentFields() {
        Estudiante student = estudiante(7L);

        EstudianteEntity result = mapper.toEstudianteEntity(student);

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
    void toEstudiante_mapsAllEntityFields() {
        EstudianteEntity entity = entity(8L);

        Estudiante result = mapper.toEstudiante(entity);

        assertNotNull(result);
        assertEquals(8L, result.getId());
        assertEquals(entity.getNombres(), result.getNombres());
        assertEquals(entity.getApellidos(), result.getApellidos());
        assertEquals(entity.getUsername(), result.getUsername());
        assertEquals(entity.getPassword(), result.getPassword());
        assertEquals(entity.getTelefono(), result.getTelefono());
        assertEquals(entity.getEdad(), result.getEdad());
        assertEquals(entity.getDireccion(), result.getDireccion());
    }

    @Test
    void toEstudianteList_mapsEveryEntity() {
        List<EstudianteEntity> entities = List.of(entity(1L), entity(2L));

        List<Estudiante> result = mapper.toEstudianteList(entities);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Ana", result.get(0).getNombres());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Ana", result.get(1).getNombres());
    }

    @Test
    void mapperMethods_returnNullForNullInput() {
        assertNull(mapper.toEstudianteEntity(null));
        assertNull(mapper.toEstudiante(null));
        assertNull(mapper.toEstudianteList(null));
    }

    private Estudiante estudiante(Long id) {
        return new Estudiante(id, "Ana", "Pérez", "ana01", "clave",
                "555123", "20", "Calle 1");
    }

    private EstudianteEntity entity(Long id) {
        return new EstudianteEntity(id, "Ana", "Pérez", "ana01", "clave",
                "555123", "20", "Calle 1");
    }
}

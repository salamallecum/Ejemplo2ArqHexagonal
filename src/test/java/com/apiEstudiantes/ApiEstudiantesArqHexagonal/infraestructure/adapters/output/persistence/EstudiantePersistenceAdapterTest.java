package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.entity.EstudianteEntity;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.mapper.EstudiantePersistenceMapper;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.repository.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstudiantePersistenceAdapterTest {

    private EstudianteRepository repository;
    private EstudiantePersistenceMapper mapper;
    private EstudiantePersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        repository = mock(EstudianteRepository.class);
        mapper = mock(EstudiantePersistenceMapper.class);
        adapter = new EstudiantePersistenceAdapter(repository, mapper);
    }

    @Test
    void findById_whenFound_mapsEntityToDomain() {
        EstudianteEntity entity = entity(1L);
        Estudiante student = estudiante(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toEstudiante(entity)).thenReturn(student);

        Optional<Estudiante> result = adapter.findById(1L);

        assertTrue(result.isPresent());
        assertSame(student, result.orElseThrow());
        verify(mapper).toEstudiante(entity);
    }

    @Test
    void findById_whenMissing_returnsEmpty() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Optional<Estudiante> result = adapter.findById(2L);

        assertTrue(result.isEmpty());
        verifyNoInteractions(mapper);
    }

    @Test
    void findAll_mapsEntitiesToDomainList() {
        List<EstudianteEntity> entities = List.of(entity(1L));
        List<Estudiante> students = List.of(estudiante(1L));
        when(repository.findAll()).thenReturn(entities);
        when(mapper.toEstudianteList(entities)).thenReturn(students);

        List<Estudiante> result = adapter.findAll();

        assertSame(students, result);
        verify(mapper).toEstudianteList(entities);
    }

    @Test
    void save_mapsBeforeAndAfterPersistence() {
        Estudiante input = estudiante(null);
        EstudianteEntity entity = entity(null);
        EstudianteEntity savedEntity = entity(3L);
        Estudiante savedStudent = estudiante(3L);
        when(mapper.toEstudianteEntity(input)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toEstudiante(savedEntity)).thenReturn(savedStudent);

        Estudiante result = adapter.save(input);

        assertSame(savedStudent, result);
        verify(mapper).toEstudianteEntity(input);
        verify(repository).save(entity);
        verify(mapper).toEstudiante(savedEntity);
    }

    @Test
    void deleteById_delegatesToRepository() {
        adapter.deleteById(4L);

        verify(repository).deleteById(4L);
    }

    @Test
    void existsByUsername_returnsRepositoryResult() {
        when(repository.existsByUsername("usuario")).thenReturn(true);

        assertTrue(adapter.existsByUsername("usuario"));

        verify(repository).existsByUsername("usuario");
    }

    private Estudiante estudiante(Long id) {
        return new Estudiante(id, "Ana", "Pérez", "usuario", "clave",
                "555123", "20", "Calle 1");
    }

    private EstudianteEntity entity(Long id) {
        return new EstudianteEntity(id, "Ana", "Pérez", "usuario", "clave",
                "555123", "20", "Calle 1");
    }
}

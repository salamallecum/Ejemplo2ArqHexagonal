package com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.service;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.ports.output.EstudiantePersistencePort;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.exception.EstudianteAlreadyExistsException;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.exception.EstudianteNotFoundException;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class EstudianteServiceTest {

    private EstudiantePersistencePort persistencePort;
    private EstudianteService service;

    @BeforeEach
    void setUp() {
        persistencePort = mock(EstudiantePersistencePort.class);
        service = new EstudianteService(persistencePort);
    }

    @Test
    void findById_whenExists_returnsEstudiante() {
        Estudiante e = new Estudiante(1L, "Nombres", "Apellidos", "user", "pass", "123", "20", "dir");
        when(persistencePort.findById(1L)).thenReturn(Optional.of(e));

        Estudiante result = service.findById(1L);

        assertSame(e, result);
        verify(persistencePort).findById(1L);
    }

    @Test
    void findById_whenNotExists_throwsNotFound() {
        when(persistencePort.findById(2L)).thenReturn(Optional.empty());

        EstudianteNotFoundException exception = assertThrows(
                EstudianteNotFoundException.class, () -> service.findById(2L));
        assertEquals("Estudiante no encontrado con id: 2", exception.getMessage());
        verify(persistencePort).findById(2L);
    }

    @Test
    void findAll_returnsStudentsFromPersistence() {
        List<Estudiante> expected = List.of(estudiante(1L), estudiante(2L));
        when(persistencePort.findAll()).thenReturn(expected);

        List<Estudiante> result = service.findAll();

        assertSame(expected, result);
        verify(persistencePort).findAll();
    }

    @Test
    void save_whenUsernameIsAvailable_savesAndReturnsStudent() {
        Estudiante newStudent = estudiante(null);
        when(persistencePort.existsByUsername(newStudent.getUsername())).thenReturn(false);
        when(persistencePort.save(newStudent)).thenReturn(newStudent);

        Estudiante result = service.save(newStudent);

        assertSame(newStudent, result);
        verify(persistencePort).existsByUsername(newStudent.getUsername());
        verify(persistencePort).save(newStudent);
    }

    @Test
    void save_whenUsernameAlreadyExists_throwsAndDoesNotSave() {
        Estudiante newStudent = estudiante(null);
        when(persistencePort.existsByUsername(newStudent.getUsername())).thenReturn(true);

        EstudianteAlreadyExistsException exception = assertThrows(
                EstudianteAlreadyExistsException.class, () -> service.save(newStudent));

        assertEquals("Ya existe un estudiante con ese username", exception.getMessage());
        verify(persistencePort).existsByUsername(newStudent.getUsername());
        verify(persistencePort, never()).save(newStudent);
    }

    @Test
    void update_whenStudentExists_updatesAllFieldsAndSaves() {
        Estudiante savedStudent = estudiante(3L);
        Estudiante changes = new Estudiante(
                null, "Nuevos nombres", "Nuevos apellidos", "nuevoUser",
                "nuevaClave", "555123", "25", "Nueva dirección");
        when(persistencePort.findById(3L)).thenReturn(Optional.of(savedStudent));
        when(persistencePort.save(savedStudent)).thenReturn(savedStudent);

        Estudiante result = service.update(3L, changes);

        assertSame(savedStudent, result);
        assertEquals(3L, result.getId());
        assertEquals("Nuevos nombres", result.getNombres());
        assertEquals("Nuevos apellidos", result.getApellidos());
        assertEquals("nuevoUser", result.getUsername());
        assertEquals("nuevaClave", result.getPassword());
        assertEquals("555123", result.getTelefono());
        assertEquals("25", result.getEdad());
        assertEquals("Nueva dirección", result.getDireccion());
        verify(persistencePort).findById(3L);
        verify(persistencePort).save(savedStudent);
    }

    @Test
    void update_whenStudentDoesNotExist_throwsNotFound() {
        when(persistencePort.findById(4L)).thenReturn(Optional.empty());

        EstudianteNotFoundException exception = assertThrows(
                EstudianteNotFoundException.class,
                () -> service.update(4L, estudiante(null)));

        assertEquals("Estudiante no encontrado con id: 4", exception.getMessage());
        verify(persistencePort).findById(4L);
        verify(persistencePort, never()).save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void deleteById_whenStudentExists_deletesIt() {
        when(persistencePort.findById(5L)).thenReturn(Optional.of(estudiante(5L)));

        service.deleteById(5L);

        verify(persistencePort).findById(5L);
        verify(persistencePort).deleteById(5L);
    }

    @Test
    void deleteById_whenStudentDoesNotExist_throwsNotFoundWithoutDeleting() {
        when(persistencePort.findById(6L)).thenReturn(Optional.empty());

        EstudianteNotFoundException exception = assertThrows(
                EstudianteNotFoundException.class, () -> service.deleteById(6L));

        assertEquals("Estudiante no encontrado con id: 6", exception.getMessage());
        verify(persistencePort).findById(6L);
        verify(persistencePort, never()).deleteById(6L);
    }

    private Estudiante estudiante(Long id) {
        return new Estudiante(id, "Nombres", "Apellidos", "usuario", "clave",
                "123456", "20", "Dirección");
    }
}

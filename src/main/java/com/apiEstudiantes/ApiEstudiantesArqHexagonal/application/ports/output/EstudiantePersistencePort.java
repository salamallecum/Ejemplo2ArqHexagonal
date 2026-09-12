package com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.ports.output;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;

import java.util.List;
import java.util.Optional;


//Interfaz que define las operaciones a nivel de persistencia que se pueden hacer con el Estudiante
//paquete input: Definimos las interfaces cue definen las operaciones a realizar con los objetos modelo del sistema
//paquete output; Definimos las interfaces que definen el guardado en base de datos, cola de mensajeria o demas
//  operaciones a realizar con los objetos para su comunicación con otros microservicios.

//Esta interfaz define las operaciones CRUD que se pueden realizar con un objeto Estudiante en Base de datos
public interface EstudiantePersistencePort {

    //Estos métodos serán las implementaciónes JPA para uso de base de datos

    //Consulta la info de uns estudiante mediante su id
    Optional<Estudiante> findById(Long id);

    //Consulta el listado de estudiantes registrados
    List<Estudiante> findAll();

    //Registra un nvo estudiante
    Estudiante save(Estudiante nvoEstudiante);

    //Elimina un estudiante
    void deleteById(Long id);
}

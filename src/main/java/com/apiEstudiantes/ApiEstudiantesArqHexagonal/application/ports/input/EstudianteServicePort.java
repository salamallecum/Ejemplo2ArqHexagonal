package com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.ports.input;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;

import java.util.List;

//Interfaz que define las operaciones que se pueden realizar con un objeto estudiante (puertos de entrada)
public interface EstudianteServicePort {

    //Estos son los casos de uso que utilizará la aplicación

    //Consulta la info de uns estudiante mediante su id
    Estudiante findById(Long id);

    //Consulta el listado de estudiantes registrados
    List<Estudiante> findAll();

    //Registra un nvo estudiante
    Estudiante save(Estudiante nvoEstudiante);

    //Actualiza la info de un estudiante
    Estudiante update(Long id, Estudiante estudianteEdit);

    //Elimina un estudiante
    void deleteById(Long id);
}

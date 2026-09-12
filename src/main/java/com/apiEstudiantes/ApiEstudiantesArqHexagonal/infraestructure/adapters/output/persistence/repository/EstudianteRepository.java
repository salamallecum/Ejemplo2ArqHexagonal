package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.repository;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Clase repository que hereda los metodos crud de jpa para la implementación de los metodos crud en bd
@Repository
public interface EstudianteRepository extends JpaRepository <EstudianteEntity, Long>{
}

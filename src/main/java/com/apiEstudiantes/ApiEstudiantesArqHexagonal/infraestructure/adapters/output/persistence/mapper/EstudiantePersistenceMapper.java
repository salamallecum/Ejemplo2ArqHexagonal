package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.mapper;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.entity.EstudianteEntity;
import org.mapstruct.Mapper;

import java.util.List;

//Interfaz encargada de definir los métodos que convierten objetos de dominio Estudiante a su respectiva entidad y viceversa
@Mapper(componentModel = "spring")
public interface EstudiantePersistenceMapper {

    //Convierte un objeto de dominio estudiante a una entidad Estudiante
    EstudianteEntity toEstudianteEntity(Estudiante estudiante);

    //Convierte una entidad Estudiante a un objeto de dominio estudiante
    Estudiante toEstudiante(EstudianteEntity estEntity);

    //Convierte un listado de objetos de dominio estudiante a un listado de entidades Estudiante
    List<Estudiante> toEstudianteList(List<EstudianteEntity> entityList);

}

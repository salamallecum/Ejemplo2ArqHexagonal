package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.mapper;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.request.SaveEstudianteRequest;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.response.EstudianteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

//Interfaz encargada de definir los métodos que convierten objetos dto de captura de datos tipo Estudiante
// a objetos de tipo dominio Estudiante y viceversa
//La instrucción unmappedTargetPolicy = ReportingPolicy.IGNORE significa que va a ignorar los campos que
// lleguen nulos en el request para evitar errores con el obj de tipo domain
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstudianteRestMapper {

    //Convierte un objeto dto estudiante a un objeto de dominio estudiante
    Estudiante toEstudiante(SaveEstudianteRequest request);

    //Convierte un objeto de dominio estudiante a un objeto de respuesta estudiante
    EstudianteResponse toEstudianteResponse(Estudiante estudiante);

    //Convierte un listado de objetos de dominio estudiante a un listado de objetos de respuesta estudiante
    List<EstudianteResponse> toEstudianteResponseList(List<Estudiante> estudianteList);

}

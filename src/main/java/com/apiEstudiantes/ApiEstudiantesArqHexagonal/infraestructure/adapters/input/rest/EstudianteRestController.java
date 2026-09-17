package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.ports.input.EstudianteServicePort;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.request.SaveEstudianteRequest;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.response.EstudianteResponse;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.mapper.EstudianteRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Clase controladora que define los endpoints de la API REST para el manejo de estudiantes
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/estudiantes")
public class EstudianteRestController {

    //Definimos un objeto del tipo servicio y mapper de estudiante para poder invocar los métodos de la capa de aplicación
    private final EstudianteServicePort estudianteServicePort;
    private final EstudianteRestMapper estudianteRestMapper;

    //EndPoint paras obtener el listado de estudiantes registrados
    @GetMapping
    public List<EstudianteResponse> findAllEstudiantes() {
        //Convertimos el listado de objetos de dominio estudiante a un listado de objetos de respuesta estudiante
        return estudianteRestMapper.toEstudianteResponseList(estudianteServicePort.findAll());
    }

    //EndPoint para obtener un estudiante por su id
    @GetMapping("/{id}")
    public EstudianteResponse findEstudianteById(@PathVariable  Long id) {
        //Convertimos el objeto de dominio estudiante a un objeto de respuesta estudiante
        return estudianteRestMapper.toEstudianteResponse(estudianteServicePort.findById(id));
    }

    //EndPoint para registrar un nuevo estudiante
    @PostMapping("/crearEstudiante")
    public ResponseEntity<EstudianteResponse> saveEstudiante(@Valid @RequestBody SaveEstudianteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estudianteRestMapper.toEstudianteResponse(estudianteServicePort.save(
                        estudianteRestMapper.toEstudiante(request))));
    }

    //Endpoint para actualizar un estudiante existente
    @PutMapping("/actualizarEstudiante/{id}")
    public EstudianteResponse updateEstudiante(@PathVariable Long id, @Valid @RequestBody SaveEstudianteRequest request) {
        return estudianteRestMapper.toEstudianteResponse(
                estudianteServicePort.update(id, estudianteRestMapper.toEstudiante(request)));
    }

    //EndPoint para eliminar un estudiante por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstudiante(@PathVariable Long id) {
        estudianteServicePort.deleteById(id);
        Map<String, Object> data = new HashMap<>();
        data.put("Mensaje", "Estudiante eliminado con éxito");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
    }

}

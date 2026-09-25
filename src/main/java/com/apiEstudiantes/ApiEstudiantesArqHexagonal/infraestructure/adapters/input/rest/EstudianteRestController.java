package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.ports.input.EstudianteServicePort;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.ErrorResponse;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.request.SaveEstudianteRequest;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.response.EstudianteResponse;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.mapper.EstudianteRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Consultar listado de estudiantes", description = "Consulta el listado de estudiantes registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna estado http 200 con el listado de estudiantes.", content = @Content(schema = @Schema(implementation = EstudianteResponse.class),
                    examples = @ExampleObject(value = "[\n" +
                            "    {\n" +
                            "        \"id\": 6,\n" +
                            "        \"nombres\": \"Pepe pecas editado\",\n" +
                            "        \"apellidos\": \"Molano\",\n" +
                            "        \"username\": \"Tatis94\",\n" +
                            "        \"password\": \"54321\",\n" +
                            "        \"telefono\": \"6726648\",\n" +
                            "        \"edad\": \"32\",\n" +
                            "        \"direccion\": \"Calle 13\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"id\": 7,\n" +
                            "        \"nombres\": \"Pepe pecas editado\",\n" +
                            "        \"apellidos\": \"Roro\",\n" +
                            "        \"username\": \"pepe98\",\n" +
                            "        \"password\": \"54321\",\n" +
                            "        \"telefono\": \"6726648\",\n" +
                            "        \"edad\": \"32\",\n" +
                            "        \"direccion\": \"Calle 13\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"id\": 8,\n" +
                            "        \"nombres\": \"Luis Alejandro\",\n" +
                            "        \"apellidos\": \"Amaya Torres\",\n" +
                            "        \"username\": \"Tutu234\",\n" +
                            "        \"password\": \"123456\",\n" +
                            "        \"telefono\": \"555555\",\n" +
                            "        \"edad\": \"29\",\n" +
                            "        \"direccion\": \"Calle 223\"\n" +
                            "    }\n" +
                            "]"))),
            @ApiResponse(responseCode = "500", description = "Retorna estado http 500 cuando ocurre un error interno en el servidor.", content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"codigoError\": \"ERR-SRV-001\",\n" +
                            "    \"mensaje\": \"Error interno del servidor.\",\n" +
                            "    \"detalles\": [\n" +
                            "        \"Se produjo un error inesperado en el servidor.\"\n" +
                            "    ],\n" +
                            "    \"timestamp\": \"2026-09-25T14:24:47.8695314\"\n" +
                            "}")))
    })
    @GetMapping
    public List<EstudianteResponse> findAllEstudiantes() {
        //Convertimos el listado de objetos de dominio estudiante a un listado de objetos de respuesta estudiante
        return estudianteRestMapper.toEstudianteResponseList(estudianteServicePort.findAll());
    }

    //EndPoint para obtener un estudiante por su id
    @Operation(summary = "Consultar estudiante", description = "Consulta la información de un estudiante existente")
    @Parameter(name = "id", description = "ID del estudiante a consultar", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna estado http 200 con la información del estudiante actualizada.", content = @Content(schema = @Schema(implementation = EstudianteResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"id\": 6,\n" +
                            "    \"nombres\": \"Pepito actualizado\",\n" +
                            "    \"apellidos\": \"Martinez\",\n" +
                            "    \"username\": \"Pepe97\",\n" +
                            "    \"password\": \"123456\",\n" +
                            "    \"telefono\": \"555555\",\n" +
                            "    \"edad\": \"35\",\n" +
                            "    \"direccion\": \"Calle 22\"\n" +
                            "}"))),
            @ApiResponse(responseCode = "500", description = "Retorna estado http 500 cuando ocurre un error interno en el servidor.", content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"codigoError\": \"ERR-SRV-001\",\n" +
                            "    \"mensaje\": \"Error interno del servidor.\",\n" +
                            "    \"detalles\": [\n" +
                            "        \"Se produjo un error inesperado en el servidor.\"\n" +
                            "    ],\n" +
                            "    \"timestamp\": \"2026-09-25T14:24:47.8695314\"\n" +
                            "}")))
    })
    @GetMapping("/{id}")
    public EstudianteResponse findEstudianteById(@PathVariable  Long id) {
        //Convertimos el objeto de dominio estudiante a un objeto de respuesta estudiante
        return estudianteRestMapper.toEstudianteResponse(estudianteServicePort.findById(id));
    }

    //EndPoint para registrar un nuevo estudiante
    @Operation(summary = "Registrar estudiante", description = "Registra un estudiante en el sistema")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto que contiene la información del estudiante a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SaveEstudianteRequest.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"nombres\": \"Pepito\",\n" +
                            "    \"apellidos\": \"Martinez\",\n" +
                            "    \"usuario\": \"Pepe97\",\n" +
                            "    \"clave\": \"123456\",\n" +
                            "    \"telefono\": \"555555\",\n" +
                            "    \"edad\": \"35\",\n" +
                            "    \"direccion\": \"Calle 22\"\n" +
                            "}")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna estado http 201 con la información del estudiante registrado.", content = @Content(schema = @Schema(implementation = EstudianteResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"id\": 7,\n" +
                            "    \"nombres\": \"Pepito\",\n" +
                            "    \"apellidos\": \"Martinez\",\n" +
                            "    \"username\": \"Pepe97\",\n" +
                            "    \"password\": \"123456\",\n" +
                            "    \"telefono\": \"555555\",\n" +
                            "    \"edad\": \"35\",\n" +
                            "    \"direccion\": \"Calle 22\"\n" +
                            "}"))),
            @ApiResponse(responseCode = "400", description = "Retorna estado http 400 cuando los datos del estudiante no son válidos.", content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"codigoError\": \"ERR-EST-002\",\n" +
                            "    \"mensaje\": \"Datos del estudiante inválidos.\",\n" +
                            "    \"detalles\": [\n" +
                            "        \"apellidos: El campo apellidos no puede estar vacío\",\n" +
                            "        \"usuario: El campo usuario no puede estar vacío\"\n" +
                            "    ],\n" +
                            "    \"timestamp\": \"2026-09-25T14:29:42.2060227\"\n" +
                            "}"))),
            @ApiResponse(responseCode = "409", description = "Retorna estado http 409 a manera de excepción cuando encuentra un estudiante registrado en el sistema con el mismo username.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "    \"codigoError\": \"ERR-EST-003\",\n" +
                                    "    \"mensaje\": \"Estudiante ya existe.\",\n" +
                                    "    \"detalles\": [\n" +
                                    "        \"Ya existe un estudiante con ese username\"\n" +
                                    "    ],\n" +
                                    "    \"timestamp\": \"2026-09-25T14:24:47.8695314\"\n" +
                                    "}"))),
            @ApiResponse(responseCode = "500", description = "Retorna estado http 500 cuando ocurre un error interno en el servidor.", content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"codigoError\": \"ERR-SRV-001\",\n" +
                            "    \"mensaje\": \"Error interno del servidor.\",\n" +
                            "    \"detalles\": [\n" +
                            "        \"Se produjo un error inesperado en el servidor.\"\n" +
                            "    ],\n" +
                            "    \"timestamp\": \"2026-09-25T14:24:47.8695314\"\n" +
                            "}")))
    })
    @PostMapping("/crearEstudiante")
    public ResponseEntity<EstudianteResponse> saveEstudiante(@Valid @RequestBody SaveEstudianteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estudianteRestMapper.toEstudianteResponse(estudianteServicePort.save(
                        estudianteRestMapper.toEstudiante(request))));
    }

    //Endpoint para actualizar un estudiante existente
     @Operation(summary = "Actualizar estudiante", description = "Actualiza la información de un estudiante existente")
    @Parameter(name = "id", description = "ID del estudiante a actualizar", required = true)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto que contiene la información del estudiante a actualizar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SaveEstudianteRequest.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"nombres\": \"Pepito\",\n" +
                            "    \"apellidos\": \"Martinez\",\n" +
                            "    \"usuario\": \"Pepe97\",\n" +
                            "    \"clave\": \"123456\",\n" +
                            "    \"telefono\": \"555555\",\n" +
                            "    \"edad\": \"35\",\n" +
                            "    \"direccion\": \"Calle 22\"\n" +
                            "}")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna estado http 200 con la información del estudiante actualizada.", content = @Content(schema = @Schema(implementation = EstudianteResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"id\": 6,\n" +
                            "    \"nombres\": \"Pepito actualizado\",\n" +
                            "    \"apellidos\": \"Martinez\",\n" +
                            "    \"username\": \"Pepe97\",\n" +
                            "    \"password\": \"123456\",\n" +
                            "    \"telefono\": \"555555\",\n" +
                            "    \"edad\": \"35\",\n" +
                            "    \"direccion\": \"Calle 22\"\n" +
                            "}"))),
            @ApiResponse(responseCode = "400", description = "Retorna estado http 400 cuando los datos del estudiante no son válidos.", content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"codigoError\": \"ERR-EST-002\",\n" +
                            "    \"mensaje\": \"Datos del estudiante inválidos.\",\n" +
                            "    \"detalles\": [\n" +
                            "        \"apellidos: El campo apellidos no puede estar vacío\",\n" +
                            "        \"usuario: El campo usuario no puede estar vacío\"\n" +
                            "    ],\n" +
                            "    \"timestamp\": \"2026-09-25T14:29:42.2060227\"\n" +
                            "}"))),
            @ApiResponse(responseCode = "409", description = "Retorna estado http 409 a manera de excepción cuando no encuentra un estudiante registrado en el sistema con el id especificado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "    \"codigoError\": \"ERR-EST-001\",\n" +
                                    "    \"mensaje\": \"Estudiante no encontrado.\",\n" +
                                    "    \"detalles\": [\n" +
                                    "        \"Estudiante no encontrado con id: 5\"\n" +
                                    "    ],\n" +
                                    "    \"timestamp\": \"2026-09-25T15:35:03.4301238\"\n" +
                                    "}"))),
            @ApiResponse(responseCode = "500", description = "Retorna estado http 500 cuando ocurre un error interno en el servidor.", content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"codigoError\": \"ERR-SRV-001\",\n" +
                            "    \"mensaje\": \"Error interno del servidor.\",\n" +
                            "    \"detalles\": [\n" +
                            "        \"Se produjo un error inesperado en el servidor.\"\n" +
                            "    ],\n" +
                            "    \"timestamp\": \"2026-09-25T14:24:47.8695314\"\n" +
                            "}")))
    })
    @PutMapping("/actualizarEstudiante/{id}")
    public EstudianteResponse updateEstudiante(@PathVariable Long id, @Valid @RequestBody SaveEstudianteRequest request) {
        return estudianteRestMapper.toEstudianteResponse(
                estudianteServicePort.update(id, estudianteRestMapper.toEstudiante(request)));
    }

    //EndPoint para eliminar un estudiante por su id
    @Operation(summary = "Eliminar estudiante", description = "Elimina un estudiante existente por su ID")
    @Parameter(name = "id", description = "ID del estudiante a eliminar", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Retorna estado http 202 indicando que el estudiante fue eliminado.", content = @Content(schema = @Schema(implementation = EstudianteResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"Mensaje\": \"Estudiante eliminado con éxito\"\n" +
                            "}"))),
            @ApiResponse(responseCode = "500", description = "Retorna estado http 500 cuando ocurre un error interno en el servidor.", content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(value = "{\n" +
                            "    \"codigoError\": \"ERR-SRV-001\",\n" +
                            "    \"mensaje\": \"Error interno del servidor.\",\n" +
                            "    \"detalles\": [\n" +
                            "        \"Se produjo un error inesperado en el servidor.\"\n" +
                            "    ],\n" +
                            "    \"timestamp\": \"2026-09-25T14:24:47.8695314\"\n" +
                            "}")))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstudiante(@PathVariable Long id) {
        estudianteServicePort.deleteById(id);
        Map<String, Object> data = new HashMap<>();
        data.put("Mensaje", "Estudiante eliminado con éxito");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
    }

}

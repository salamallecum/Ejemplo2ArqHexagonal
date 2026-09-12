package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.input.rest.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Clase Dto encargada de definir el objeto que recibe como parámetro los endpoint de registro y actualización Estudiante
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveEstudianteRequest {

    @Size(max = 30, message = "Los nombres del estudiante no puede tener más de 30 caracteres")
    @NotBlank(message = "El campo nombres no puede estar vacío")
    @JsonProperty("nombres")
    private String nombres;

    @Size(max = 30, message = "Los apellidos del estudiante no pueden tener más de 30 caracteres")
    @NotBlank(message = "El campo apellidos no puede estar vacío")
    @JsonProperty("apellidos")
    private String apellidos;

    @Size(max = 10, message = "El nombre de usuario del estudiante no puede tener más de 10 caracteres")
    @NotBlank(message = "El campo usuario no puede estar vacío")
    @JsonProperty("usuario")
    private String username;

    @Size(max = 10, message = "El password del estudiante no puede tener más de 10 caracteres")
    @NotBlank(message = "El campo clave no puede estar vacío")
    @JsonProperty("clave")
    private String password;

    @Size(max = 10, message = "El telefono del estudiante no puede tener más de 10 caracteres")
    @NotBlank(message = "El campo telefono no puede estar vacío")
    @Pattern(regexp = "\\d+", message = "El campo teléfono solo debe contener números")
    @JsonProperty("telefono")
    private String telefono;

    @Size(max = 3, message = "La edad del estudiante no puede tener más de 3  caracteres")
    @NotBlank(message = "El campo edad no puede estar vacío")
    @Pattern(regexp = "\\d+", message = "El campo edad solo debe contener números")
    @JsonProperty("edad")
    private String edad;

    @Size(max = 50, message = "La dirección del estudiante no pueden tener más de 50 caracteres")
    @NotBlank(message = "El campo direccion no puede estar vacío")
    @JsonProperty("direccion")
    private String direccion;
}

package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

import java.util.List;

//Clase encargada de la configuración e información principal de la aplicación que tendrá la documentación Swagger
@OpenAPIDefinition(
        info = @Info(
                //Info de la aplicación e info de contacto del desarrollador
                title = "API Estudiantes con arquitectura hexagonal",
                description = "EndPoints con la lógica de negocio.",
                version = "1.0.0",
                contact = @Contact(name = "Luis Alejandro Amaya Torres", url = "https://miportafolioweb.ifree.page/?i=1", email = "luisalejandroamayatorres@gmail.com")
        ),
        security = @SecurityRequirement(name = "Security token") //Aquí definimos el esquema de seguridad que tendrá la documentación
)

public class DocumentationConfig {

    //Bean encargado de corregir la url de acceso a la documentación
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new io.swagger.v3.oas.models.servers.Server().url("")));
    }
}

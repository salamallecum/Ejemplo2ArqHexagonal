package com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.service;

//Clase que sobreescribe los métodos de la interfaz que define los puertos de entrada (EstudianteServicePort)
//haciendo uso de los métodos definidos en la interfaz de salida para persistencia en BD (EstudiantePersistencePort)
//En esta capa es donde definimos la logica de negocio especial que pueda tener el estudiante, por ejemplo la existencia
//previa de un nombre de usuario, login, etc

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.ports.input.EstudianteServicePort;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.ports.output.EstudiantePersistencePort;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.exception.EstudianteNotFoundException;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //Con esto indicamos que se requiere constructor con los parámetros definidos para la clase
public class EstudianteService implements EstudianteServicePort {

    //Definimos un objeto que define los puertos de salida (EstudiantePersistencePort)
    private final EstudiantePersistencePort persistencePort;

    //------------------Metodos de la intefaz de entrada (EstudianteServicePort)---------------//
    @Override
    public Estudiante findById(Long id) {
        return persistencePort.findById(id)
                .orElseThrow(EstudianteNotFoundException::new);
    }

    @Override
    public List<Estudiante> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public Estudiante save(Estudiante nvoEstudiante) {
        return persistencePort.save(nvoEstudiante);
    }

    @Override
    public Estudiante update(Long id, Estudiante estudianteEdit) {
        //Localizamos mediante el id el estudiante y en caso de que exista sobreescribe su info en BD
        // si no lo encuentra retornará la excepción que definimos
        return persistencePort.findById(id)
                        .map(estudianteSaved -> {
                            estudianteSaved.setNombres(estudianteEdit.getNombres());
                            estudianteSaved.setApellidos(estudianteEdit.getApellidos());
                            estudianteSaved.setUsername(estudianteEdit.getUsername());
                            estudianteSaved.setPassword(estudianteEdit.getPassword());
                            estudianteSaved.setTelefono(estudianteEdit.getTelefono());
                            estudianteSaved.setEdad(estudianteEdit.getEdad());
                            estudianteSaved.setDireccion(estudianteEdit.getDireccion());
                            return persistencePort.save(estudianteSaved);
                        })
                        .orElseThrow(EstudianteNotFoundException::new);

    }

    @Override
    public void deleteById(Long id) {
        //Localizamos mediante el id el estudiante y en caso de que exista lo eliminamos de la BD
        // si no lo encuentra retornará la excepción que definimos
        if(persistencePort.findById(id).isEmpty()){
            throw new EstudianteNotFoundException();
        }
        persistencePort.deleteById(id);
    }
}

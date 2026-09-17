package com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence;

import com.apiEstudiantes.ApiEstudiantesArqHexagonal.application.ports.output.EstudiantePersistencePort;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.domain.model.Estudiante;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.mapper.EstudiantePersistenceMapper;
import com.apiEstudiantes.ApiEstudiantesArqHexagonal.infraestructure.adapters.output.persistence.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//Clase adaptador encargada del manejo de la persistencia (Implementa y sobreescribe los métodos de la interfaz
// EstudiantePersistencePort definida en la capa application)
@Component
@RequiredArgsConstructor
public class EstudiantePersistenceAdapter implements EstudiantePersistencePort {

    //Definimos un objeto de tipo repositorio y de tipo adapter
    private final EstudianteRepository repository;
    private final EstudiantePersistenceMapper mapper;

    //------------------Métodos de la intefaz de entrada (EstudianteServicePort)---------------//

    @Override
    public Optional<Estudiante> findById(Long id) {
        //Localizamos el estudiante en bd y en caso de que exista lo mapeamos al objeto de domino respectivamente
        return repository.findById(id)
                .map(mapper::toEstudiante);
    }

    @Override
    public List<Estudiante> findAll() {
        //Mapeamos el listado y lo retornamos
        return mapper.toEstudianteList(repository.findAll());
    }

    @Override
    public Estudiante save(Estudiante nvoEstudiante) {
        //Mapeamos el objeto a la entidad correspondiente y la retornamos
        return mapper.toEstudiante(repository.save(mapper.toEstudianteEntity(nvoEstudiante)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}

package alkemy.challenge.disney.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import alkemy.challenge.disney.dto.FiltroPersonajeDto;
import alkemy.challenge.disney.dto.PersonajeBasicoDto;
import alkemy.challenge.disney.dto.PersonajeDto;
import alkemy.challenge.disney.entidades.PersonajeEntidad;
import alkemy.challenge.disney.mapeos.PersonajeMapeo;
import alkemy.challenge.disney.repositorios.PersonajeRepositorio;
import alkemy.challenge.disney.repositorios.especificaciones.PersonajeEspecificacion;

@Service
public class PersonajeServicio {
    
    @Autowired
    private PersonajeRepositorio personajeRepositorio;
    @Autowired
    private PersonajeMapeo personajeMapeo;
    @Autowired
    private PersonajeEspecificacion especificacion;

    @Transactional
    public PersonajeDto guardar(PersonajeDto personajeDto){
        PersonajeEntidad entidadPersonajeGuardado = personajeRepositorio.save(personajeMapeo.dto2Entidad(personajeDto));
        PersonajeDto dtoPersonajeGuardado = personajeMapeo.entidad2Dto(entidadPersonajeGuardado);
        return dtoPersonajeGuardado;
    }

    @Transactional
    public PersonajeDto actualizar(Long personajeId, PersonajeDto personajeDto) throws Exception{
        Optional<PersonajeEntidad> personaje = personajeRepositorio.findById(personajeId);
        if (!personaje.isPresent()){
            throw new Exception("El id no es valido");
        }

        PersonajeEntidad personajeGuardado = personaje.get();
        PersonajeEntidad personajeActualizado = personajeMapeo.dto2Entidad(personajeDto, personajeGuardado);
        personajeActualizado = personajeRepositorio.save(personajeActualizado);

        return personajeMapeo.entidad2Dto(personajeActualizado);
        
    }

    @Transactional(readOnly = true)
    public PersonajeDto buscarPorId(Long personajeId) throws Exception{
        Optional<PersonajeEntidad> personaje = personajeRepositorio.findById(personajeId);
        if (!personaje.isPresent()){
            throw new Exception("El id no es valido");
        }
        PersonajeDto personajeDto = personajeMapeo.entidad2Dto(personaje.get());
        return personajeDto;
    }

    @Transactional
    public void eliminarPorId(Long personajeId) throws Exception{
        Optional<PersonajeEntidad> personaje = personajeRepositorio.findById(personajeId);
        if (!personaje.isPresent()){
            throw new Exception("El id no es valido");
        }
        personajeRepositorio.delete(personaje.get());
    }

    @Transactional(readOnly = true)
    public List<PersonajeBasicoDto> buscarPorFiltros(String nombre, Integer edad, Set<Long> peliculasId){
        FiltroPersonajeDto filtroPersonajeDto = new FiltroPersonajeDto(nombre, edad, peliculasId);
        List<PersonajeEntidad> entidadesPersonajes = personajeRepositorio.findAll(especificacion.buscarPorFiltros(filtroPersonajeDto));
        List<PersonajeBasicoDto> dtoPersonajes = personajeMapeo.listaEntidades2listaDtoBasico(entidadesPersonajes);
        return dtoPersonajes;
    }
}

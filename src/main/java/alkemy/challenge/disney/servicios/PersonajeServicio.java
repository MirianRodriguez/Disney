package alkemy.challenge.disney.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import alkemy.challenge.disney.dto.PersonajeDto;
import alkemy.challenge.disney.entidades.PersonajeEntidad;
import alkemy.challenge.disney.mapeos.PersonajeMapeo;
import alkemy.challenge.disney.repositorios.PersonajeRepositorio;

@Service
public class PersonajeServicio {
    
    @Autowired
    private PersonajeRepositorio personajeRepositorio;
    @Autowired
    private PersonajeMapeo personajeMapeo;

    @Transactional
    public PersonajeDto guardar(PersonajeDto personajeDto){
        PersonajeEntidad entidadPersonajeGuardado = personajeRepositorio.save(personajeMapeo.dto2Entidad(personajeDto));
        PersonajeDto dtoGeneroGuardado = personajeMapeo.entidad2Dto(entidadPersonajeGuardado);
        return dtoGeneroGuardado;
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
    public PersonajeDto buscarPorId(Long personajeId){
        PersonajeEntidad personajeEntidad = personajeRepositorio.findById(personajeId).get();
        PersonajeDto personajeDto = personajeMapeo.entidad2Dto(personajeEntidad);
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
}

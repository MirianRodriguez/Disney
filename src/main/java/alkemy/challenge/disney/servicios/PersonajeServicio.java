package alkemy.challenge.disney.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PersonajeDto guardar(PersonajeDto personajeDto){
        PersonajeEntidad entidadPersonajeGuardado = personajeRepositorio.save(personajeMapeo.dto2Entidad(personajeDto));
        PersonajeDto dtoGeneroGuardado = personajeMapeo.entidad2Dto(entidadPersonajeGuardado);
        return dtoGeneroGuardado;
    }
}

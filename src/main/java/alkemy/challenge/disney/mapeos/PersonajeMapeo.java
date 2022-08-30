package alkemy.challenge.disney.mapeos;

import org.springframework.stereotype.Component;

import alkemy.challenge.disney.dto.PersonajeDto;
import alkemy.challenge.disney.entidades.PersonajeEntidad;

@Component
public class PersonajeMapeo {
    
    public PersonajeEntidad dto2Entidad(PersonajeDto personajeDto){
        PersonajeEntidad personajeEntidad = new PersonajeEntidad();
        personajeEntidad.setNombre(personajeDto.getNombre());
        personajeEntidad.setImagen(personajeDto.getImagen());
        personajeEntidad.setEdad(personajeDto.getEdad());
        personajeEntidad.setPeso(personajeDto.getPeso());
        personajeEntidad.setHistoria(personajeDto.getHistoria());
        return personajeEntidad;
    }

    public PersonajeDto entidad2Dto(PersonajeEntidad personajeEntidad){
        PersonajeDto personajeDto = new PersonajeDto();
        personajeDto.setPersonajeId(personajeEntidad.getPersonajeId());
        personajeDto.setNombre(personajeEntidad.getNombre());
        personajeDto.setImagen(personajeEntidad.getImagen());
        personajeDto.setEdad(personajeEntidad.getEdad());
        personajeDto.setPeso(personajeEntidad.getPeso());
        personajeDto.setHistoria(personajeEntidad.getHistoria());
        return personajeDto;
    }
}

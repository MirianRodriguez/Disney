package alkemy.challenge.disney.mapeos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import alkemy.challenge.disney.dto.PersonajeBasicoDto;
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

    public PersonajeEntidad dto2Entidad(PersonajeDto personajeDto, PersonajeEntidad personajeEntidad){
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

    public List<PersonajeBasicoDto>listaEntidades2listaDtoBasico(List<PersonajeEntidad> entidadesPersonajes){
        List<PersonajeBasicoDto> dtoPersonajes = new ArrayList<>();
        PersonajeBasicoDto personajeBasicoDto;
        for (PersonajeEntidad personajeEntidad : entidadesPersonajes) {
            personajeBasicoDto = new PersonajeBasicoDto();
            personajeBasicoDto.setNombre(personajeEntidad.getNombre());
            personajeBasicoDto.setImagen(personajeEntidad.getImagen());
            dtoPersonajes.add(personajeBasicoDto);
        }
        return dtoPersonajes;
    }

    public List<PersonajeDto>listaEntidades2listaDto(List<PersonajeEntidad> entidadesPersonajes){
        List<PersonajeDto> dtoPersonajes = new ArrayList<>();
        PersonajeDto personajeDto;
        for (PersonajeEntidad personajeEntidad : entidadesPersonajes) {
            personajeDto = new PersonajeDto();
            personajeDto.setPersonajeId(personajeEntidad.getPersonajeId());
            personajeDto.setNombre(personajeEntidad.getNombre());
            personajeDto.setImagen(personajeEntidad.getImagen());
            personajeDto.setEdad(personajeEntidad.getEdad());
            personajeDto.setPeso(personajeEntidad.getPeso());
            personajeDto.setHistoria(personajeEntidad.getHistoria());
            dtoPersonajes.add(personajeDto);
        }
        return dtoPersonajes;
    }
}

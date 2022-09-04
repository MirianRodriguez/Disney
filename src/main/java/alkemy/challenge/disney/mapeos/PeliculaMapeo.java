package alkemy.challenge.disney.mapeos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import alkemy.challenge.disney.dto.GeneroDto;
import alkemy.challenge.disney.dto.PeliculaBasicoDto;
import alkemy.challenge.disney.dto.PeliculaDto;
import alkemy.challenge.disney.dto.PersonajeDto;
import alkemy.challenge.disney.entidades.GeneroEntidad;
import alkemy.challenge.disney.entidades.PeliculaEntidad;
import alkemy.challenge.disney.entidades.PersonajeEntidad;
import alkemy.challenge.disney.servicios.GeneroServicio;
import alkemy.challenge.disney.servicios.PersonajeServicio;


@Component
public class PeliculaMapeo {

    @Autowired
    private GeneroServicio generoServicio;
    @Autowired
    private PersonajeMapeo personajeMapeo;
    @Autowired
    private PersonajeServicio personajeServicio;
    @Autowired
    private GeneroMapeo generoMapeo;

    public PeliculaEntidad dto2Entidad(PeliculaDto peliculaDto) throws Exception{
        PeliculaEntidad peliculaEntidad = new PeliculaEntidad();
        GeneroDto genero = generoServicio.buscarPorId(peliculaDto.getGeneroId());
        GeneroEntidad generoAsociado = generoMapeo.dto2Entidad(genero);
        peliculaEntidad.setTitulo(peliculaDto.getTitulo());
        peliculaEntidad.setImagen(peliculaDto.getImagen());
        peliculaEntidad.setFechaCreacion(peliculaDto.getFechaCreacion());
        peliculaEntidad.setCalificacion(peliculaDto.getCalificacion());
        peliculaEntidad.setGeneroId(generoAsociado);
        List<PersonajeEntidad> personajesEntidad = new ArrayList<>();
        for (PersonajeDto personajeDto : peliculaDto.getPersonajes()) {
            PersonajeDto personajeDtoExistente = personajeServicio.buscarPorId(personajeDto.getPersonajeId());
            personajesEntidad.add(personajeMapeo.dto2Entidad(personajeDtoExistente));
        }
        peliculaEntidad.setPersonajes(personajesEntidad);
        return peliculaEntidad;
    }

    public PeliculaEntidad dto2Entidad(PeliculaDto peliculaDto, PeliculaEntidad peliculaEntidad) throws Exception{
        GeneroDto genero = generoServicio.buscarPorId(peliculaDto.getGeneroId());
        GeneroEntidad generoAsociado = generoMapeo.dto2Entidad(genero);
        peliculaEntidad.setTitulo(peliculaDto.getTitulo());
        peliculaEntidad.setImagen(peliculaDto.getImagen());
        peliculaEntidad.setFechaCreacion(peliculaDto.getFechaCreacion());
        peliculaEntidad.setCalificacion(peliculaDto.getCalificacion());
        peliculaEntidad.setGeneroId(generoAsociado);
        return peliculaEntidad;
    }

    public PeliculaDto entidad2Dto(PeliculaEntidad peliculaEntidad, boolean cargarPersonajes){
        PeliculaDto peliculaDto = new PeliculaDto();
        peliculaDto.setPeliculaId(peliculaEntidad.getPeliculaId());
        peliculaDto.setTitulo(peliculaEntidad.getTitulo());
        peliculaDto.setImagen(peliculaEntidad.getImagen());
        peliculaDto.setFechaCreacion(peliculaEntidad.getFechaCreacion());
        peliculaDto.setCalificacion(peliculaEntidad.getCalificacion());
        peliculaDto.setGeneroId(peliculaEntidad.getGeneroId().getGeneroId());
        if(cargarPersonajes){
            List<PersonajeDto> personajesDto = personajeMapeo.listaEntidades2listaDto(peliculaEntidad.getPersonajes());
            peliculaDto.setPersonajes(personajesDto);
        }
        return peliculaDto;
    }

    public List<PeliculaBasicoDto> listaEntidades2listaDtoBasico(List<PeliculaEntidad> entidadesPeliculas) {
        List<PeliculaBasicoDto> dtoPeliculas = new ArrayList<>();
        PeliculaBasicoDto peliculaBasicoDto;
        for (PeliculaEntidad peliculaEntidad : entidadesPeliculas) {
            peliculaBasicoDto = new PeliculaBasicoDto();
            peliculaBasicoDto.setTitulo(peliculaEntidad.getTitulo());
            peliculaBasicoDto.setImagen(peliculaEntidad.getImagen());
            peliculaBasicoDto.setFechaCreacion(peliculaEntidad.getFechaCreacion());
            dtoPeliculas.add(peliculaBasicoDto);
        }
        return dtoPeliculas;
    }

    public List<PeliculaDto> listaEntidades2listaDto(List<PeliculaEntidad> entidadesPeliculas) {
        List<PeliculaDto> dtoPeliculas = new ArrayList<>();
        PeliculaDto peliculaDto;
        for (PeliculaEntidad peliculaEntidad : entidadesPeliculas) {
            peliculaDto = new PeliculaDto();
            peliculaDto.setPeliculaId(peliculaEntidad.getPeliculaId());
            peliculaDto.setTitulo(peliculaEntidad.getTitulo());
            peliculaDto.setImagen(peliculaEntidad.getImagen());
            peliculaDto.setFechaCreacion(peliculaEntidad.getFechaCreacion());
            peliculaDto.setCalificacion(peliculaEntidad.getCalificacion());
            peliculaDto.setGeneroId(peliculaEntidad.getGeneroId().getGeneroId());
            dtoPeliculas.add(peliculaDto);
        }
        return dtoPeliculas;
    }

}

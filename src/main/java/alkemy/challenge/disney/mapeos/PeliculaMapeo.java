package alkemy.challenge.disney.mapeos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import alkemy.challenge.disney.dto.PeliculaBasicoDto;
import alkemy.challenge.disney.dto.PeliculaDto;
import alkemy.challenge.disney.dto.PersonajeDto;
import alkemy.challenge.disney.entidades.GeneroEntidad;
import alkemy.challenge.disney.entidades.PeliculaEntidad;
import alkemy.challenge.disney.repositorios.GeneroRepositorio;


@Component
public class PeliculaMapeo {

    @Autowired
    private GeneroRepositorio generoRepositorio;
    @Autowired
    private PersonajeMapeo personajeMapeo;

    public PeliculaEntidad dto2Entidad(PeliculaDto peliculaDto){
        PeliculaEntidad peliculaEntidad = new PeliculaEntidad();
        GeneroEntidad generoAsociado = generoRepositorio.findById(peliculaDto.getGeneroId()).get();
        peliculaEntidad.setTitulo(peliculaDto.getTitulo());
        peliculaEntidad.setImagen(peliculaDto.getImagen());
        peliculaEntidad.setFechaCreacion(peliculaDto.getFechaCreacion());
        peliculaEntidad.setCalificacion(peliculaDto.getCalificacion());
        peliculaEntidad.setGeneroId(generoAsociado);
        // for (Long personajeId : peliculaDto.getPersonajes()) {
            
        // }
        //peliculaEntidad.setPersonajes(peliculaDto.getPersonajes());
        return peliculaEntidad;
    }

    public PeliculaEntidad dto2Entidad(PeliculaDto peliculaDto, PeliculaEntidad peliculaEntidad){
        GeneroEntidad generoAsociado = generoRepositorio.findById(peliculaDto.getGeneroId()).get();
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

    public List<PeliculaBasicoDto> listaEntidades2listaDto(List<PeliculaEntidad> entidadesPeliculas) {
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

}

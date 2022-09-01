package alkemy.challenge.disney.mapeos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import alkemy.challenge.disney.dto.PeliculaDto;
import alkemy.challenge.disney.entidades.GeneroEntidad;
import alkemy.challenge.disney.entidades.PeliculaEntidad;
import alkemy.challenge.disney.repositorios.GeneroRepositorio;


@Component
public class PeliculaMapeo {

    @Autowired
    private GeneroRepositorio generoRepositorio;

    public PeliculaEntidad dto2Entidad(PeliculaDto peliculaDto){
        PeliculaEntidad peliculaEntidad = new PeliculaEntidad();
        GeneroEntidad generoAsociado = generoRepositorio.findById(peliculaDto.getGeneroId()).get();
        peliculaEntidad.setTitulo(peliculaDto.getTitulo());
        peliculaEntidad.setImagen(peliculaDto.getImagen());
        peliculaEntidad.setFechaCreacion(peliculaDto.getFechaCreacion());
        peliculaEntidad.setCalificacion(peliculaDto.getCalificacion());
        peliculaEntidad.setGeneroId(generoAsociado);
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

    public PeliculaDto entidad2Dto(PeliculaEntidad peliculaEntidad){
        PeliculaDto peliculaDto = new PeliculaDto();
        peliculaDto.setPeliculaId(peliculaEntidad.getPeliculaId());
        peliculaDto.setTitulo(peliculaEntidad.getTitulo());
        peliculaDto.setImagen(peliculaEntidad.getImagen());
        peliculaDto.setFechaCreacion(peliculaEntidad.getFechaCreacion());
        peliculaDto.setCalificacion(peliculaEntidad.getCalificacion());
        peliculaDto.setGeneroId(peliculaEntidad.getGeneroId().getGeneroId());
        return peliculaDto;
    }

}

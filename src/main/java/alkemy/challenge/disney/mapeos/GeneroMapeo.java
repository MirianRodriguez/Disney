package alkemy.challenge.disney.mapeos;

import org.springframework.stereotype.Component;

import alkemy.challenge.disney.dto.GeneroDto;
import alkemy.challenge.disney.entidades.GeneroEntidad;

@Component
public class GeneroMapeo {

    public GeneroEntidad dto2Entidad(GeneroDto generoDto){
        GeneroEntidad generoEntidad = new GeneroEntidad();
        generoEntidad.setGeneroId(generoDto.getGeneroId());
        generoEntidad.setNombre(generoDto.getNombre());
        generoEntidad.setImagen(generoDto.getImagen());
        return generoEntidad;
    }

    public GeneroDto entidad2Dto(GeneroEntidad generoEntidad){
        GeneroDto generoDto = new GeneroDto();
        generoDto.setGeneroId(generoEntidad.getGeneroId());
        generoDto.setNombre(generoEntidad.getNombre());
        generoDto.setImagen(generoEntidad.getImagen());
        return generoDto;
    }
}

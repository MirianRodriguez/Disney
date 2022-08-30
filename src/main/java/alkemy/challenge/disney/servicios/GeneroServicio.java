package alkemy.challenge.disney.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alkemy.challenge.disney.dto.GeneroDto;
import alkemy.challenge.disney.entidades.GeneroEntidad;
import alkemy.challenge.disney.mapeos.GeneroMapeo;
import alkemy.challenge.disney.repositorios.GeneroRepositorio;

@Service
public class GeneroServicio {

    @Autowired
    private GeneroRepositorio generoRepositorio;

    @Autowired
    private GeneroMapeo generoMapeo;
    
    public GeneroDto guardar(GeneroDto generoDto){
        GeneroEntidad entidadGeneroGuardado = generoRepositorio.save(generoMapeo.dto2Entidad(generoDto));
        GeneroDto dtoGeneroGuardado = generoMapeo.entidad2Dto(entidadGeneroGuardado);
        return dtoGeneroGuardado;
    }
}

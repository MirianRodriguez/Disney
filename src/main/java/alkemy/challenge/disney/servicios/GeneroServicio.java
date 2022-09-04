package alkemy.challenge.disney.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Transactional
    public GeneroDto guardar(GeneroDto generoDto){
        GeneroEntidad entidadGeneroGuardado = generoRepositorio.save(generoMapeo.dto2Entidad(generoDto));
        GeneroDto dtoGeneroGuardado = generoMapeo.entidad2Dto(entidadGeneroGuardado);
        return dtoGeneroGuardado;
    }

    @Transactional(readOnly = true)
    public GeneroDto buscarPorId(Long generoId) throws Exception {
        Optional<GeneroEntidad> genero = generoRepositorio.findById(generoId);
        if (!genero.isPresent()){
            throw new Exception("El id del genero no es valido");
        }
        GeneroDto generoDto = generoMapeo.entidad2Dto(genero.get());
        return generoDto;
    }
}

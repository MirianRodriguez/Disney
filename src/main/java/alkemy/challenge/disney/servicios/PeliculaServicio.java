package alkemy.challenge.disney.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import alkemy.challenge.disney.dto.FiltroPeliculaDto;
import alkemy.challenge.disney.dto.PeliculaBasicoDto;
import alkemy.challenge.disney.dto.PeliculaDto;
import alkemy.challenge.disney.entidades.PeliculaEntidad;
import alkemy.challenge.disney.entidades.PersonajeEntidad;
import alkemy.challenge.disney.mapeos.PeliculaMapeo;
import alkemy.challenge.disney.repositorios.PeliculaRepositorio;
import alkemy.challenge.disney.repositorios.PersonajeRepositorio;
import alkemy.challenge.disney.repositorios.especificaciones.PeliculaEspecificacion;

@Service
public class PeliculaServicio {
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private PersonajeRepositorio personajeRepositorio;
    @Autowired
    private PeliculaMapeo peliculaMapeo;
    @Autowired
    private PeliculaEspecificacion especificacion;

    @Transactional
    public PeliculaDto guardar(PeliculaDto peliculaDto) throws Exception{
        PeliculaEntidad entidadPeliculaGuardada = peliculaRepositorio.save(peliculaMapeo.dto2Entidad(peliculaDto));
        PeliculaDto dtoPeliculaGuardada = peliculaMapeo.entidad2Dto(entidadPeliculaGuardada, true);
        return dtoPeliculaGuardada;
    }

    @Transactional
    public PeliculaDto actualizar(Long peliculaId, PeliculaDto peliculaDto) throws Exception{
        Optional<PeliculaEntidad> pelicula = peliculaRepositorio.findById(peliculaId);
        if (!pelicula.isPresent()){
            throw new Exception("El id de pelicula no es valido");
        }

        PeliculaEntidad peliculaGuardada = pelicula.get();
        PeliculaEntidad peliculaActualizada = peliculaMapeo.dto2Entidad(peliculaDto, peliculaGuardada);
        peliculaActualizada = peliculaRepositorio.save(peliculaActualizada);

        return peliculaMapeo.entidad2Dto(peliculaActualizada, true);
        
    }

    @Transactional(readOnly = true)
    public PeliculaDto buscarPorId(Long peliculaId) throws Exception{
        Optional<PeliculaEntidad> pelicula = peliculaRepositorio.findById(peliculaId);
        if (!pelicula.isPresent()){
            throw new Exception("El id de pelicula no es valido");
        }
        PeliculaDto peliculaDto = peliculaMapeo.entidad2Dto(pelicula.get(), true);
        return peliculaDto;
    }

    @Transactional
    public void eliminarPorId(Long peliculaId) throws Exception{
        Optional<PeliculaEntidad> pelicula = peliculaRepositorio.findById(peliculaId);
        if (!pelicula.isPresent()){
            throw new Exception("El id de pelicula no es valido");
        }
        peliculaRepositorio.delete(pelicula.get());
    }

    @Transactional(readOnly = true)
    public List<PeliculaBasicoDto> buscarPorFiltros(String titulo, Long generoId, String orden) {
        FiltroPeliculaDto filtroPeliculaDto = new FiltroPeliculaDto(titulo, generoId, orden);
        List<PeliculaEntidad> entidadesPeliculas = peliculaRepositorio.findAll(especificacion.buscarPorFiltros(filtroPeliculaDto));
        List<PeliculaBasicoDto> dtoPeliculas = peliculaMapeo.listaEntidades2listaDtoBasico(entidadesPeliculas);
        return dtoPeliculas;
    }

    @Transactional
    public PeliculaDto agregarPersonaje(Long peliculaId, Long personajeId) throws Exception{
        Optional<PeliculaEntidad> peliculaBuscada = peliculaRepositorio.findById(peliculaId);
        if(!peliculaBuscada.isPresent()){
            throw new Exception("El id de la pelicula no es valido");
        }
        Optional<PersonajeEntidad> personajeBuscado = personajeRepositorio.findById(personajeId);
        if(!personajeBuscado.isPresent()){
            throw new Exception("El id del personaje no es valido");
        }
        PeliculaEntidad pelicula = peliculaBuscada.get();
        List<PersonajeEntidad> personajesPelicula = pelicula.getPersonajes();
        personajesPelicula.add(personajeBuscado.get());
        pelicula.setPersonajes(personajesPelicula);
        pelicula = peliculaRepositorio.save(pelicula);
        return peliculaMapeo.entidad2Dto(pelicula, true);
    }

    @Transactional
    public PeliculaDto removerPersonaje(Long peliculaId, Long personajeId) throws Exception{
        Optional<PeliculaEntidad> peliculaBuscada = peliculaRepositorio.findById(peliculaId);
        if(!peliculaBuscada.isPresent()){
            throw new Exception("El id de la pelicula no es valido");
        }
        Optional<PersonajeEntidad> personajeBuscado = personajeRepositorio.findById(personajeId);
        if(!personajeBuscado.isPresent()){
            throw new Exception("El id del personaje no es valido");
        }
        PeliculaEntidad pelicula = peliculaBuscada.get();
        List<PersonajeEntidad> personajesPelicula = pelicula.getPersonajes();
        if(personajesPelicula.contains(personajeBuscado.get())){
            personajesPelicula.remove(personajeBuscado.get());
        }
        pelicula.setPersonajes(personajesPelicula);
        pelicula = peliculaRepositorio.save(pelicula);
        return peliculaMapeo.entidad2Dto(pelicula, true);
    }
}

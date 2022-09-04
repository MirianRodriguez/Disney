package alkemy.challenge.disney.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import alkemy.challenge.disney.dto.PeliculaBasicoDto;
import alkemy.challenge.disney.dto.PeliculaDto;
import alkemy.challenge.disney.servicios.PeliculaServicio;

@Controller
@RequestMapping("peliculas")
public class PeliculaControlador {
    @Autowired
    private PeliculaServicio peliculaServicio;

    @PostMapping
    public ResponseEntity<PeliculaDto> crear(@RequestBody PeliculaDto peliculaDto) throws Exception{
        PeliculaDto peliculaGuardada = peliculaServicio.guardar(peliculaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }

    @GetMapping("/{peliculaId}")
    public ResponseEntity<PeliculaDto> detallePelicula(@PathVariable Long peliculaId) throws Exception{
        PeliculaDto pelicula = peliculaServicio.buscarPorId(peliculaId);
        return ResponseEntity.status(HttpStatus.OK).body(pelicula);
    }

    @PutMapping("/{peliculaId}")
    public ResponseEntity<PeliculaDto> actualizar(
        @PathVariable Long peliculaId, 
        @RequestBody PeliculaDto peliculaDto) throws Exception{
        PeliculaDto peliculaActualizada = peliculaServicio.actualizar(peliculaId, peliculaDto);
        return ResponseEntity.ok().body(peliculaActualizada);
    }

    @DeleteMapping("/{peliculaId}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long peliculaId) throws Exception{
        peliculaServicio.eliminarPorId(peliculaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<PeliculaBasicoDto>> buscarPorFiltros(
        @RequestParam(required = false) String titulo,
        @RequestParam(required = false) Long generoId,
        @RequestParam(required = false, defaultValue = "ASC") String orden
    ){
        List<PeliculaBasicoDto> peliculas = peliculaServicio.buscarPorFiltros(titulo, generoId, orden);
        return ResponseEntity.ok(peliculas);
    }

    @PostMapping("/{peliculaId}/personajes/{personajeId}")
    public ResponseEntity<PeliculaDto> agregarPersonaje(@PathVariable Long peliculaId, @PathVariable Long personajeId) throws Exception{
        PeliculaDto peliculaDto = peliculaServicio.agregarPersonaje(peliculaId, personajeId);
        return ResponseEntity.ok(peliculaDto);
    }

    @DeleteMapping("/{peliculaId}/personajes/{personajeId}")
    public ResponseEntity<PeliculaDto> removerPersonaje(@PathVariable Long peliculaId, @PathVariable Long personajeId) throws Exception{
        PeliculaDto peliculaDto = peliculaServicio.removerPersonaje(peliculaId, personajeId);
        return ResponseEntity.ok(peliculaDto);
    }

}

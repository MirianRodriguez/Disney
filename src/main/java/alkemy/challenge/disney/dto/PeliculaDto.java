package alkemy.challenge.disney.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaDto {
    private Long peliculaId;
    private String titulo;
    private String imagen;
    private LocalDate fechaCreacion;
    private Integer calificacion;
    private Long generoId;
    //private List<PersonajeEntidad> personajes = new ArrayList<>();
    
}

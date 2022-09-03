package alkemy.challenge.disney.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaBasicoDto {
    private String titulo;
    private String imagen;
    private LocalDate fechaCreacion;
}

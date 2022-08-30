package alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeDto {
    private Long personajeId;
    private String nombre;
    private String imagen;
    private Integer edad;
    private Double peso;
    private String historia;
    //agregar peliculas
}

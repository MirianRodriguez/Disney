package alkemy.challenge.disney.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroPersonajeDto {
    private String nombre; 
    private Integer edad; 
    private Set<Long> peliculasId;
    public FiltroPersonajeDto(String nombre, Integer edad, Set<Long> peliculasId) {
        this.nombre = nombre;
        this.edad = edad;
        this.peliculasId = peliculasId;
    }

    
    
}

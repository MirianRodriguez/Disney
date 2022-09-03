package alkemy.challenge.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroPeliculaDto {
    private String titulo; 
    private Long generoId; 
    private String orden;

    public FiltroPeliculaDto(String titulo, Long generoId, String orden) {
        this.titulo = titulo;
        this.generoId = generoId;
        this.orden = orden;
    }
   
}

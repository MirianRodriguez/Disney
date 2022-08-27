package alkemy.challenge.disney.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name = "genero")
@Getter
@Setter
public class GeneroEntidad {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "genero_id")
    private Long generoId;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "eliminado")
    private Boolean eliminado = Boolean.FALSE;

    @OneToMany(mappedBy = "generoId", cascade = CascadeType.ALL)
    private List<PeliculaEntidad> peliculas = new ArrayList<>();
    
}

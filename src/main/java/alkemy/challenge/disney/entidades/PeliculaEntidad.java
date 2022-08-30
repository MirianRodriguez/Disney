package alkemy.challenge.disney.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.FetchType.EAGER;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name = "pelicula")
@Getter
@Setter
@SQLDelete(sql = "UPDATE pelicula SET eliminado = true WHERE pelicula_id = ?")
@Where(clause = "eliminado = false")
public class PeliculaEntidad {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "pelicula_id")
    private Long peliculaId;

    @Column(name = "titulo", length = 50)
    private String titulo;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "fecha_creacion", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;

    @Column(name = "calificacion")
    private Integer calificacion;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "genero_id", referencedColumnName = "genero_id")
    private GeneroEntidad generoId;

    @Column(name = "eliminado")
    private Boolean eliminado = Boolean.FALSE;

    @ManyToMany
    @JoinTable(
        name = "personaje_pelicula", 
        joinColumns = @JoinColumn(name = "pelicula_id"),
        inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<PersonajeEntidad> personajes = new ArrayList<>();
}

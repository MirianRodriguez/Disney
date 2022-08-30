package alkemy.challenge.disney.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name = "personaje")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET eliminado = true WHERE personaje_id = ?")
@Where(clause = "eliminado = false")
public class PersonajeEntidad {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "personaje_id")
    private Long personajeId;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "peso", columnDefinition = "DECIMAL(10,2)")
    private Double peso;

    @Column(name = "historia")
    private String historia;

    @Column(name = "eliminado")
    private Boolean eliminado = Boolean.FALSE;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PeliculaEntidad> peliculas = new ArrayList<>();
    
}
package alkemy.challenge.disney.repositorios.especificaciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import alkemy.challenge.disney.dto.FiltroPersonajeDto;
import alkemy.challenge.disney.entidades.PeliculaEntidad;
import alkemy.challenge.disney.entidades.PersonajeEntidad;

@Component
public class PersonajeEspecificacion {

    public Specification<PersonajeEntidad> buscarPorFiltros(FiltroPersonajeDto filtroPersonajeDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicados = new ArrayList<>();

            if (StringUtils.hasLength(filtroPersonajeDto.getNombre())){
                predicados.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")), 
                        "%" + filtroPersonajeDto.getNombre().toLowerCase() + "%"
                    )
                );
            }

            if(filtroPersonajeDto.getEdad()!=null){
                predicados.add(
                    criteriaBuilder.equal(
                        root.get("edad"),
                        filtroPersonajeDto.getEdad()
                    )
                );
            }

            if(!CollectionUtils.isEmpty(filtroPersonajeDto.getPeliculasId())){
                Join<PeliculaEntidad, PersonajeEntidad> join = root.join("peliculas", JoinType.INNER);
                Expression<String> peliculasId = join.get("peliculaId");
                predicados.add(
                    peliculasId.in(filtroPersonajeDto.getPeliculasId())
                );
            }

            query.distinct(true);

            return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
        };
    }
    
}

package alkemy.challenge.disney.repositorios.especificaciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import alkemy.challenge.disney.dto.FiltroPeliculaDto;
import alkemy.challenge.disney.entidades.PeliculaEntidad;

@Component
public class PeliculaEspecificacion {
    public Specification<PeliculaEntidad> buscarPorFiltros(FiltroPeliculaDto filtroPeliculaDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicados = new ArrayList<>();

            if (StringUtils.hasLength(filtroPeliculaDto.getTitulo())){
                predicados.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("titulo")), 
                        "%" + filtroPeliculaDto.getTitulo().toLowerCase() + "%"
                    )
                );
            }

            if(filtroPeliculaDto.getGeneroId()!=null){
                predicados.add(
                    criteriaBuilder.equal(
                        root.get("generoId"),
                        filtroPeliculaDto.getGeneroId()
                    )
                );
            }

            String campoOrden = "titulo";
            if(filtroPeliculaDto.getOrden().toUpperCase().equals("ASC")){
                query.orderBy(criteriaBuilder.asc(root.get(campoOrden)));
            }else{
                query.orderBy(criteriaBuilder.desc(root.get(campoOrden)));
            }

            query.distinct(true);

            return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
        };
    }
}

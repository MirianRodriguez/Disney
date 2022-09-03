package alkemy.challenge.disney.repositorios;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alkemy.challenge.disney.entidades.PeliculaEntidad;

@Repository
public interface PeliculaRepositorio extends JpaRepository<PeliculaEntidad, Long>{
    
    List<PeliculaEntidad> findAll(Specification<PeliculaEntidad> spec);

}

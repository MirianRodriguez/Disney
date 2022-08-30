package alkemy.challenge.disney.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alkemy.challenge.disney.entidades.PeliculaEntidad;

@Repository
public interface PeliculaRepositorio extends JpaRepository<PeliculaEntidad, Long>{
    
}

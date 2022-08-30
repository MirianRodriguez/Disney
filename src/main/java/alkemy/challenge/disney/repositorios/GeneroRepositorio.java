package alkemy.challenge.disney.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alkemy.challenge.disney.entidades.GeneroEntidad;

@Repository
public interface GeneroRepositorio extends JpaRepository<GeneroEntidad, Long> {
    
}

package alkemy.challenge.disney.repositorios;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alkemy.challenge.disney.entidades.PersonajeEntidad;

@Repository
public interface PersonajeRepositorio extends JpaRepository<PersonajeEntidad, Long>{

    List<PersonajeEntidad> findAll(Specification<PersonajeEntidad> spec);
    
}

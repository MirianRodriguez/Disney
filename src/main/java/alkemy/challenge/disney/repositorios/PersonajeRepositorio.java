package alkemy.challenge.disney.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alkemy.challenge.disney.entidades.PersonajeEntidad;

@Repository
public interface PersonajeRepositorio extends JpaRepository<PersonajeEntidad, Long>{
    
}

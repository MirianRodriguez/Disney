package alkemy.challenge.disney.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alkemy.challenge.disney.dto.PersonajeDto;
import alkemy.challenge.disney.servicios.PersonajeServicio;

@RestController
@RequestMapping("personajes")
public class PersonajeControlador {
    
    @Autowired
    private PersonajeServicio personajeServicio;

    @PostMapping
    public ResponseEntity<PersonajeDto> crear(@RequestBody PersonajeDto personajeDto){
        PersonajeDto personajeGuardado = personajeServicio.guardar(personajeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }
}

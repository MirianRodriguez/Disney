package alkemy.challenge.disney.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alkemy.challenge.disney.dto.GeneroDto;
import alkemy.challenge.disney.servicios.GeneroServicio;

@RestController
@RequestMapping("generos")
public class GeneroControlador {

    @Autowired
    private GeneroServicio generoServicio;

    @PostMapping
    public ResponseEntity<GeneroDto> crear(@RequestBody GeneroDto generoDto){
        GeneroDto generoGuardado = generoServicio.guardar(generoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }
    
}

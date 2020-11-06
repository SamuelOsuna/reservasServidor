package com.samuel.reservas.controllers;

import com.samuel.reservas.exceptions.RecordNotFoundException;
import com.samuel.reservas.model.Usuario;
import com.samuel.reservas.services.UsuarioService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Samuel Osuna Alcaide
 */

@RestController
@RequestMapping("/usuario")
public class UsuarioServiceController {
    @Autowired
    UsuarioService service;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> list = service.getAllUsuarios();
        
        return new ResponseEntity<List<Usuario>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) throws RecordNotFoundException{
        Usuario entity = service.getUsuarioById(id);
        
        return new ResponseEntity<Usuario>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario miUsuario){
        Usuario created = service.createUsuario(miUsuario);
        return new ResponseEntity<Usuario>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Usuario> UpdateUsuario(@Valid @RequestBody Usuario miUsuario) throws RecordNotFoundException{
        Usuario updated = service.createUsuario(miUsuario);
        return new ResponseEntity<Usuario>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public HttpStatus deleteUsuarioById(@PathVariable("id") Long id) throws RecordNotFoundException{
        service.deleteUsuarioById(id);
        return HttpStatus.ACCEPTED;
    }
}

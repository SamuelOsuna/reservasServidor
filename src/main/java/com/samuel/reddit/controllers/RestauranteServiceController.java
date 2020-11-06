package com.samuel.reddit.controllers;

import com.samuel.reddit.exceptions.RecordNotFoundException;
import com.samuel.reddit.model.Restaurante;
import com.samuel.reddit.services.RestauranteService;
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
@RequestMapping("/restaurante")
public class RestauranteServiceController {
    @Autowired
    RestauranteService service;
    
    @GetMapping
    public ResponseEntity<List<Restaurante>> getAllRestaurantes(){
        List<Restaurante> list = service.getAllRestaurantes();
        
        return new ResponseEntity<List<Restaurante>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestauranteById(@PathVariable("id") Long id) throws RecordNotFoundException{
        Restaurante entity = service.getRestauranteById(id);
        
        return new ResponseEntity<Restaurante>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    /*@PostMapping
    public ResponseEntity<Restaurante> createRestaurante(@Valid @RequestBody Restaurante miRestaurante){
        Restaurante created = service.createRestaurante(miRestaurante);
        return new ResponseEntity<Restaurante>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Restaurante> UpdateRestaurante(@Valid @RequestBody Restaurante miRestaurante) throws RecordNotFoundException{
        Restaurante updated = service.createRestaurante(miRestaurante);
        return new ResponseEntity<Restaurante>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public HttpStatus deleteRestauranteById(@PathVariable("id") Long id) throws RecordNotFoundException{
        service.deleteRestauranteById(id);
        return HttpStatus.ACCEPTED;
    }*/
}

package com.samuel.reddit.controllers;

import com.samuel.reddit.exceptions.RecordNotFoundException;
import com.samuel.reddit.model.Reserva;
import com.samuel.reddit.services.ReservaService;
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
@RequestMapping("/reserva")
public class ReservaServiceController {
    
    @Autowired
    ReservaService service;
    
    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas(){
        List<Reserva> list = service.getAllReservas();
        
        return new ResponseEntity<List<Reserva>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable("id") Long id) throws RecordNotFoundException{
        Reserva entity = service.getReservaById(id);
        
        return new ResponseEntity<Reserva>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Reserva> createReserva(@Valid @RequestBody Reserva miReserva){
        Reserva created = service.createReserva(miReserva);
        return new ResponseEntity<Reserva>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Reserva> UpdateReserva(@Valid @RequestBody Reserva miReserva) throws RecordNotFoundException{
        Reserva updated = service.createReserva(miReserva);
        return new ResponseEntity<Reserva>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public HttpStatus deleteReservaById(@PathVariable("id") Long id) throws RecordNotFoundException{
        service.deleteReservaById(id);
        return HttpStatus.ACCEPTED;
    }
}

package com.samuel.reddit.controllers;

import com.samuel.reddit.exceptions.RecordNotFoundException;
import com.samuel.reddit.model.Mesa;
import com.samuel.reddit.services.MesaService;
import java.util.List;
import javax.validation.Valid;
import javax.xml.ws.Response;
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
@RequestMapping("/mesa")
public class MesaServiceController {
    
    @Autowired
    MesaService service;
    
    @GetMapping
    public ResponseEntity<List<Mesa>> getAllMesas(){
        List<Mesa> list = service.getAllMesas();
        
        return new ResponseEntity<List<Mesa>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getMesaById(@PathVariable("id") Long id) throws RecordNotFoundException{
        Mesa entity = service.getMesarById(id);
        
        return new ResponseEntity<Mesa>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Mesa> createMesa(@Valid @RequestBody Mesa miMesa){
        Mesa created = service.createMesa(miMesa);
        return new ResponseEntity<Mesa>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Mesa> UpdateMesa(@Valid @RequestBody Mesa miMesa) throws RecordNotFoundException{
        Mesa updated = service.createMesa(miMesa);
        return new ResponseEntity<Mesa>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public HttpStatus deleteMesaById(@PathVariable("id") Long id) throws RecordNotFoundException{
        service.deleteMesaById(id);
        return HttpStatus.ACCEPTED;
    }
}

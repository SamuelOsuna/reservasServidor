package com.samuel.reddit.services;

import com.samuel.reddit.exceptions.RecordNotFoundException;
import com.samuel.reddit.model.Restaurante;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.samuel.reddit.repositories.RestauranteRepository;


/**
 *
 * @author Samuel Osuna Alcaide
 */

@Service
public class RestauranteService{
    @Autowired
    RestauranteRepository repository;
    
    public List<Restaurante> getAllRestaurantes(){
        List<Restaurante> restauranteList = repository.findAll();
        
        if(restauranteList.size() > 0){
            return restauranteList;
        }else{
            return new ArrayList<Restaurante>();
        }
    }
    
    public Restaurante getRestauranteById (Long id) throws RecordNotFoundException{
        Optional<Restaurante> restaurante = repository.findById(id);
        
        if(restaurante.isPresent()){
            return restaurante.get();
        }else{
            throw new RecordNotFoundException("No existe ningun restaurante con el id proporcionado", id);
        }
    }
    
    /*public Restaurante createRestaurante(Restaurante entity){
        entity = repository.save(entity);
        return entity;
    }
    
    public Restaurante updateRestaurante(Restaurante entity) throws RecordNotFoundException{
        
            Optional<Restaurante> restaurante = repository.findById(entity.getId());
            
            if(restaurante.isPresent()){
                Restaurante newEntity = restaurante.get();
                newEntity.setContent(entity.getContent());
                
                newEntity = repository.save(newEntity);
                
                return newEntity;
            }else{
                throw new RecordNotFoundException("Restaurante no encontrado", entity.getId());
            }
        
    }
    
    public void deleteRestauranteById(Long id) throws RecordNotFoundException{
        Optional<Restaurante> restaurante = repository.findById(id);
        
        if(restaurante.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No existe ningun restaurante con el id proporcionado", id);
        }
    }*/
}

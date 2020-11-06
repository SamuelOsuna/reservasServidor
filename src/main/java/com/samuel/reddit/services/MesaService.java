package com.samuel.reddit.services;

import com.samuel.reddit.exceptions.RecordNotFoundException;
import com.samuel.reddit.model.Mesa;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.samuel.reddit.repositories.MesaRepository;

/**
 *
 * @author Samuel Osuna Alcaide
 */

@Service
public class MesaService {
    @Autowired
    MesaRepository repository;
    
    public List<Mesa> getAllMesas(){
        List<Mesa> mesasList = repository.findAll();
        
        if(mesasList.size() > 0){
            return mesasList;
        }else{
            return new ArrayList<Mesa>();
        }
    }
    
    public Mesa getMesarById (Long id) throws RecordNotFoundException{
        Optional<Mesa> mesa = repository.findById(id);
        
        if(mesa.isPresent()){
            return mesa.get();
        }else{
            throw new RecordNotFoundException("No existe ninguna mesa con el id proporcionado", id);
        }
    }
    
    public Mesa createMesa(Mesa entity){
        entity = repository.save(entity);
        return entity;
    }
    
    public Mesa updateMesa(Mesa entity) throws RecordNotFoundException{
        if(entity.getId()!=null){
            Optional<Mesa> mesa = repository.findById(entity.getId());
            
            if(mesa.isPresent()){
                Mesa newEntity = mesa.get();
                newEntity.setComensales(entity.getComensales());
                newEntity.setImagen(entity.getImagen());
                
                newEntity = repository.save(newEntity);
                
                return newEntity;
            }else{
                throw new RecordNotFoundException("Mesa no encontrada", entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No se ha proporcionado el id de mesa", 0l);
        }
    }
    
    public void deleteMesaById(Long id) throws RecordNotFoundException{
        Optional<Mesa> mesa = repository.findById(id);
        
        if(mesa.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No existe ninguna mesa con el id proporcionado", id);
        }
    }
}

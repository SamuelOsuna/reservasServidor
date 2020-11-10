package com.samuel.reservas.services;

import com.samuel.reservas.exceptions.RecordNotFoundException;
import com.samuel.reservas.model.Reserva;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.samuel.reservas.repositories.ReservaRepository;

/**
 *
 * @author Samuel Osuna Alcaide
 */

@Service
public class ReservaService {
    @Autowired
    ReservaRepository repository;
    
    public List<Reserva> getAllReservas(){
        List<Reserva> reservaList = repository.findAll();
        
        if(reservaList.size() > 0){
            return reservaList;
        }else{
            return new ArrayList<Reserva>();
        }
    }
    
    public Reserva getReservaById (Long id) throws RecordNotFoundException{
        Optional<Reserva> reserva = repository.findById(id);
        
        if(reserva.isPresent()){
            return reserva.get();
        }else{
            throw new RecordNotFoundException("No existe ninguna reserva con el id proporcionado", id);
        }
    }
    
    public Reserva createReserva(Reserva entity){
        entity = repository.save(entity);
        return entity;
    }
    
    public Reserva updateReserva(Reserva entity) throws RecordNotFoundException{
        if(entity.getId()!=null){
            Optional<Reserva> reserva = repository.findById(entity.getId());
            
            if(reserva.isPresent()){
                Reserva newEntity = reserva.get();
                newEntity.setFecha(entity.getFecha());
                newEntity.setAceptada(entity.isAceptada());
                newEntity.setTipo(entity.getTipo());
                newEntity.setMesa(entity.getMesa());
                
                newEntity = repository.save(newEntity);
                
                return newEntity;
            }else{
                throw new RecordNotFoundException("Reserva no encontrada", entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No se ha proporcionado el id de Reserva", 0l);
        }
    }
    
    public void deleteReservaById(Long id) throws RecordNotFoundException{
        Optional<Reserva> reserva = repository.findById(id);
        
        if(reserva.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No existe ninguna reserva con el id proporcionado", id);
        }
    }
}

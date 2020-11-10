package com.samuel.reservas.services;

import com.samuel.reservas.exceptions.RecordNotFoundException;
import com.samuel.reservas.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.samuel.reservas.repositories.UsuarioRepository;

/**
 *
 * @author Samuel Osuna Alcaide
 */

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;
    
    public List<Usuario> getAllUsuarios(){
        List<Usuario> usuarioList = repository.findAll();
        
        if(usuarioList.size() > 0){
            return usuarioList;
        }else{
            return new ArrayList<Usuario>();
        }
    }
    
    public Usuario getUsuarioById (Long id) throws RecordNotFoundException{
        Optional<Usuario> usuario = repository.findById(id);
        
        if(usuario.isPresent()){
            return usuario.get();
        }else{
            throw new RecordNotFoundException("No existe ningun usuario con el id proporcionado", id);
        }
    }
    
    public Usuario createUsuario(Usuario entity){
        entity = repository.save(entity);
        return entity;
    }
    
    public Usuario updateUsuario(Usuario entity) throws RecordNotFoundException{
        if(entity.getId()!=null){
            Optional<Usuario> usuario = repository.findById(entity.getId());
            
            if(usuario.isPresent()){
                Usuario newEntity = usuario.get();
                newEntity.setNombre(entity.getNombre());
                newEntity.setEmail(entity.getEmail());
                newEntity.setContrasena(entity.getContrasena());
                newEntity.setImagen(entity.getImagen());
                
                newEntity = repository.save(newEntity);
                
                return newEntity;
            }else{
                throw new RecordNotFoundException("Usuario no encontrado", entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No se ha proporcionado el id de usuario", 0l);
        }
    }
    
    public void deleteUsuarioById(Long id) throws RecordNotFoundException{
        Optional<Usuario> usuario = repository.findById(id);
        
        if(usuario.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No existe ningun usuario con el id proporcionado", id);
        }
    }
}

package com.samuel.reservas.repositories;

import com.samuel.reservas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Samuel Osuna Alcaide
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}

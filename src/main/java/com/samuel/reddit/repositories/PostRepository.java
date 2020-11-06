package com.samuel.reddit.repositories;

import com.samuel.reddit.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Samuel Osuna Alcaide
 */
public interface PostRepository extends JpaRepository<Usuario, Long>{
    
}

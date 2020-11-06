package com.samuel.reddit.repositories;

import com.samuel.reddit.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samuel Osuna Alcaide
 */

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
    
}

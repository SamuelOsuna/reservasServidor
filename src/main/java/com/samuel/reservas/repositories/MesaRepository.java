package com.samuel.reservas.repositories;

import com.samuel.reservas.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samuel Osuna Alcaide
 */

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long>{
    
}

package com.samuel.reservas.repositories;

import com.samuel.reservas.model.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samuel Osuna Alcaide
 */

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    
}

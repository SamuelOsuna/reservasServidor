package com.samuel.reddit.repositories;

import com.samuel.reddit.model.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samuel Osuna Alcaide
 */

@Repository
public interface SubredditRepository extends JpaRepository<Reserva, Long>{

    @Query(
    value="SELECT subreddit.id,subreddit.name,subreddit,description FROM subreddit WHERE subreddit.name LIKE %?1%", nativeQuery = true)
    public List<Reserva> getByName(String name);
    
}

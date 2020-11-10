package com.samuel.reservas.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.samuel.reservas.model.*;
import com.samuel.reservas.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MesaResolver implements GraphQLResolver<Mesa> {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public MesaResolver(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante getRestaurante(Mesa mesa) {
        return restauranteRepository.findById(mesa.getRestaurante().getId()).orElseThrow(null);
    }
}

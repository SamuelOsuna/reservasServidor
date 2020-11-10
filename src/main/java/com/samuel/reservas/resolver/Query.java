package com.samuel.reservas.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.samuel.reservas.model.*;
import com.samuel.reservas.repositories.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javassist.NotFoundException;

@Component
public class Query implements GraphQLQueryResolver {

    private UsuarioRepository usuarioRepository;
    private RestauranteRepository restauranteRepository;
    private ReservaRepository reservaRepository;
    private MesaRepository mesaRepository;

    @Autowired
    public Query(UsuarioRepository usuarioRepository,
            RestauranteRepository restauranteRepository,
            ReservaRepository reservaRepository,
            MesaRepository mesaRepository) {

        this.usuarioRepository = usuarioRepository;
        this.reservaRepository = reservaRepository;
        this.restauranteRepository = restauranteRepository;
        this.mesaRepository = mesaRepository;
    }

    public Iterable<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Iterable<Usuario> findUsuariosByNombre(String nombre) {
        List<Usuario> finalUsuarios = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (!usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                if (usuario.getNombre() == nombre) {
                    finalUsuarios.add(usuario);
                }
            }
        }
        return finalUsuarios;
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(null);
    }

    public Long countUsuarios() {
        return usuarioRepository.count();
    }

    public Iterable<Restaurante> findAllRestaurantes() {
        return restauranteRepository.findAll();
    }

    public Restaurante findRestauranteById(Long id) {
        return restauranteRepository.findById(id).orElseThrow(null);
    }

    public Iterable<Reserva> findReservasByRestaurante(Long id) throws NotFoundException {
        Restaurante rest = restauranteRepository.findById(id).orElseThrow(null);
        if (!rest.getReservas().isEmpty()) {
            return rest.getReservas();
        }
        throw new NotFoundException("Este restaurante no tiene reservas");
    }

    public Iterable<Reserva> findReservasByUsuario(Long id) throws NotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(null);
        if (!usuario.getReservas().isEmpty()) {
            return usuario.getReservas();
        }
        throw new NotFoundException("Este usuario no tiene reservas");
    }

    public Iterable<Mesa> findAllMesasByRestaurante(Long id) throws NotFoundException {
        Restaurante rest = restauranteRepository.findById(id).orElseThrow(null);
        if (!rest.getMesas().isEmpty()) {
            return rest.getMesas();
        }
        throw new NotFoundException("Este restaurante no tiene mesas");
    }
}

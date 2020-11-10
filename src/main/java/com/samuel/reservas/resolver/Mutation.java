package com.samuel.reservas.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import com.samuel.reservas.model.*;
import com.samuel.reservas.repositories.*;
import java.util.Optional;
import javassist.NotFoundException;

public class Mutation implements GraphQLMutationResolver {
    //https://bezkoder.com/spring-boot-graphql-mysql-jpa/
    private UsuarioRepository usuarioRepository;
    private RestauranteRepository restauranteRepository;
    private ReservaRepository reservaRepository;
    private MesaRepository mesaRepository;
    
    @Autowired
    public Mutation(
            UsuarioRepository usuarioRepository,
            RestauranteRepository restauranteRepository,
            ReservaRepository reservaRepository,
            MesaRepository mesaRepository) {
        
        this.usuarioRepository = usuarioRepository;
        this.restauranteRepository = restauranteRepository;
        this.reservaRepository = reservaRepository;
        this.mesaRepository = mesaRepository;
        
    }
    
    public Usuario createUsuario(String nombre, String email, String contrasena, String imagen) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContrasena(contrasena);
        
        if (imagen != null) {
            usuario.setImagen(imagen);
        }
        
        usuarioRepository.save(usuario);
        
        return usuario;
    }
    
    public boolean deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
        return true;
    }
    
    public Usuario updateUsuario(Long id, String nombre, String email, String contrasena, String imagen) throws NotFoundException {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            if (nombre != null) {
                usuario.setNombre(nombre);
            }
            if (email != null) {
                usuario.setEmail(email);
            }
            if (contrasena != null) {
                usuario.setContrasena(contrasena);
            }
            if (imagen != null) {
                usuario.setImagen(imagen);
            }
            
            usuarioRepository.save(usuario);
            return usuario;
        }
        throw new NotFoundException("No se ha encontrado ningún usuario con este ID");
    }
    
    public Reserva createReserva(Long id_usuario, Long id_restaurante, Long id_mesa, String fecha, String tipo) {
        Reserva reserva = new Reserva();
        Usuario usuario = usuarioRepository.findById(id_usuario).orElseThrow(null);
        Restaurante restaurante = restauranteRepository.findById(id_restaurante).orElseThrow(null);
        Mesa mesa = mesaRepository.findById(id_mesa).orElseThrow(null);
        
        reserva.setUsuario(usuario);
        reserva.setRestaurante(restaurante);
        reserva.setMesa(mesa);
        reserva.setFecha(fecha);
        reserva.setTipo(tipo);
        
        reservaRepository.save(reserva);
        
        return reserva;
    }
    
    public boolean deleteReserva(Long id_reserva) {
        reservaRepository.deleteById(id_reserva);
        return true;
    }
    
    public Mesa createMesa(Long id_restaurante, int nmesa, int comensales, String imagen) throws NotFoundException {
        Mesa mesa = new Mesa();
        Restaurante restaurante = restauranteRepository.findById(id_restaurante).orElseThrow(null);
        
        if (restaurante != null) {
            mesa.setRestaurante(restaurante);
            mesa.setNmesa(nmesa);
            mesa.setComensales(comensales);
            
            if (imagen != null) {
                mesa.setImagen(imagen);
            }
            
            mesaRepository.save(mesa);
            return mesa;
            
        }
        throw new NotFoundException("No se ha encontrado ningun restaurante con este ID");
        
    }
    
    public boolean deleteMesa(Long id_mesa) {
        mesaRepository.deleteById(id_mesa);
        return true;
    }
    
    public Mesa updateMesa(Long id_mesa, Integer nmesa, Integer comensales, String imagen) throws NotFoundException {
        Optional<Mesa> optMesa = mesaRepository.findById(id_mesa);
        
        if (optMesa.isPresent()) {
            Mesa mesa = optMesa.get();
            
            if (nmesa != null) {
                mesa.setNmesa(nmesa);
            }
            if (comensales != null) {
                mesa.setComensales(comensales);
            }
            if (imagen != null) {
                mesa.setImagen(imagen);
            }
            
            mesaRepository.save(mesa);
            return mesa;
        }
        throw new NotFoundException("No se ha encontrado ninguna mesa con este ID");
    }
    
}

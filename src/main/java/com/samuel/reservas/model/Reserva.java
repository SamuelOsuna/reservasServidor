package com.samuel.reservas.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Samuel Osuna Alcaide
 */
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @MapsId("id_usuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nmesa")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @MapsId("id_mesa")
    @JoinColumn(name = "id_mesa")
    private Mesa mesa;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @MapsId("id_restaurante")
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha")
    private String fecha;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date fechacreacion;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "aceptada", nullable = true)
    @JsonIgnore
    private Boolean aceptada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public void setAceptada(boolean aceptada) {
        this.aceptada = aceptada;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", usuario=" + usuario + ", mesa=" + mesa + ", restaurante=" + restaurante + ", nombre=" + nombre + ", fecha=" + fecha + ", fechacreacion=" + fechacreacion + ", tipo=" + tipo + ", aceptada=" + aceptada + '}';
    }

}

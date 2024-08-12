package com.cac.autord.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "nick", length = 20 , unique = true, nullable = false)
    private String nick;

    @Column(name = "email", length = 80, unique = true, nullable = false)
    private String email;

    @Column(name = "pass", length = 20, nullable = false)
    private String pass;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;

    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;

    @Column(name = "movil", nullable = false)
    private Long movil;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LibroEntity> libros;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderEntity> ordenes;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ComentarioEntity> comentarios;


    //Constructor

    public UsuarioEntity() {
    }

    public UsuarioEntity(String nick, String email, String pass, String nombre, String apellido, String direccion, Long movil) {
        this.nick = nick;
        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.movil = movil;
    }

    //getters y setters

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getMovil() {
        return movil;
    }

    public void setMovil(Long movil) {
        this.movil = movil;
    }

    public List<LibroEntity> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroEntity> libros) {
        this.libros = libros;
    }

    public List<OrderEntity> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<OrderEntity> ordenes) {
        this.ordenes = ordenes;
    }

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }
}

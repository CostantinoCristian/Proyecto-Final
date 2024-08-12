package com.cac.autord.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class LibroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro", nullable = false)
    private Long idLibro;

    @Column(name= "titulo",nullable = false, columnDefinition = "TEXT")
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonIgnore
    private UsuarioEntity idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_tematica", referencedColumnName = "id_tematica")
    @JsonIgnore
    private TematicaEntity idTematica;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private boolean publicado;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "detalles", columnDefinition = "TEXT")
    private String detalles;

    @OneToMany(mappedBy = "idLibro", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PaginaEntity> paginas;

    @OneToMany(mappedBy = "idLibro", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ComentarioEntity> comentarios;

    //Constructores

    public LibroEntity() {
    }

    public LibroEntity(String titulo, UsuarioEntity idUsuario, TematicaEntity idTematica, boolean publicado, Double precio, String detalles) {
        this.titulo = titulo;
        this.idUsuario = idUsuario;
        this.idTematica = idTematica;
        this.publicado = publicado;
        this.precio = precio;
        this.detalles = detalles;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public UsuarioEntity getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioEntity idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TematicaEntity getIdTematica() {
        return idTematica;
    }

    public void setIdTematica(TematicaEntity idTematica) {
        this.idTematica = idTematica;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<PaginaEntity> getPaginas() {
        return paginas;
    }

    public void setPaginas(List<PaginaEntity> paginas) {
        this.paginas = paginas;
    }

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}



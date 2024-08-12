package com.cac.autord.persistence.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "tematicas")
public class TematicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tematica", nullable = false)
    private Long idTematica;

    @Column(name = "tema", nullable = false, unique = true)
    private String tema;

    @OneToMany(mappedBy = "idTematica", cascade = CascadeType.ALL)
    private List<LibroEntity> librosTematica;


    public TematicaEntity() {
    }

    public TematicaEntity(String tema) {
        this.tema = tema;
    }

    public Long getIdTematica() {
        return idTematica;
    }

    public void setIdTematica(Long idTematica) {
        this.idTematica = idTematica;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public List<LibroEntity> getLibrosTematica() {
        return librosTematica;
    }

    public void setLibrosTematica(List<LibroEntity> librosTematica) {
        this.librosTematica = librosTematica;
    }
}

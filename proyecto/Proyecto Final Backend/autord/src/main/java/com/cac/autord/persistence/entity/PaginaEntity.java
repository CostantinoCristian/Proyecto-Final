package com.cac.autord.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "paginas")
public class PaginaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pagina")
    private Long idPagina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")
    @JsonIgnore
    private LibroEntity idLibro;

    @Column(name= "num_pagina")
    private Integer numPagina;

    @Column(name = "texto", columnDefinition = "TEXT")
    private String texto;

    public PaginaEntity() {
    }

    public PaginaEntity(LibroEntity idLibro, Integer numPagina, String texto) {
        this.idLibro = idLibro;
        this.numPagina = numPagina;
        this.texto = texto;
    }

    public Long getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(Long idPagina) {
        this.idPagina = idPagina;
    }

    public LibroEntity getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(LibroEntity idLibro) {
        this.idLibro = idLibro;
    }

    public Integer getNumPagina() {
        return numPagina;
    }

    public void setNumPagina(Integer numPagina) {
        this.numPagina = numPagina;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
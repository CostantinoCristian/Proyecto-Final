package com.cac.autord.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compras")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonIgnore
    private UsuarioEntity idUsuario;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @OneToMany(mappedBy = "idCompra", cascade = CascadeType.ALL)
    private List<OrderLibroEntity> itemsCompra;

    public OrderEntity() {
    }

    public OrderEntity(UsuarioEntity comprador, LocalDateTime fechaCompra) {
        this.idUsuario = comprador;
        this.fechaCompra = fechaCompra;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public UsuarioEntity getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioEntity idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<OrderLibroEntity> getItemsCompra() {
        return itemsCompra;
    }

    public void setItemsCompra(List<OrderLibroEntity> itemsCompra) {
        this.itemsCompra = itemsCompra;
    }
}


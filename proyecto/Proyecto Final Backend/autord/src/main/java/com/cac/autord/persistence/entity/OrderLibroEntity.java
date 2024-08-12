package com.cac.autord.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "compras_libros")
public class OrderLibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_compra")
    private Long idItemCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra", referencedColumnName = "id_compra")
    @JsonIgnore
    private OrderEntity idCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")
    @JsonIgnore
    private LibroEntity idLibro;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "total")
    private Double total;

    public OrderLibroEntity() {
    }

    public OrderLibroEntity(OrderEntity idCompra, LibroEntity idLibro, Integer cantidad, Double total) {
        this.idCompra = idCompra;
        this.idLibro = idLibro;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Long getIdItemCompra() {
        return idItemCompra;
    }

    public void setIdItemCompra(Long idItemCompra) {
        this.idItemCompra = idItemCompra;
    }

    public OrderEntity getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(OrderEntity idCompra) {
        this.idCompra = idCompra;
    }

    public LibroEntity getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(LibroEntity idLibro) {
        this.idLibro = idLibro;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}


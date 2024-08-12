package com.cac.autord.service;
import com.cac.autord.persistence.entity.OrderEntity;
import com.cac.autord.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository= orderRepository;
    }


    //metodo que retornara una lista de todos los valores dentro de la tabla
    public List<OrderEntity> getAll() {
        return this.orderRepository.findAll();
    }


    //metodo que recupera un valor segun el id
    public OrderEntity get(Long idOrder) {
        return this.orderRepository.findById(idOrder).orElse(null);
    }

    //metodo para añadir o modificar un elemento de la tabla
    public OrderEntity save(OrderEntity order) {
        return this.orderRepository.save(order);
    }

    //metodo para borrar un elemento de la tabla
    public void delete(Long id) {
        this.orderRepository.deleteById(id);
    }
    //metodo que verifica si existe en la tabla el elemento con esa id
    public boolean exist(Long id) {
        return this.orderRepository.existsById(id);
    }
}


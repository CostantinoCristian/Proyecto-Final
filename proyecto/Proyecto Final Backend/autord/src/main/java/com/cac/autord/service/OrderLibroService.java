package com.cac.autord.service;
import com.cac.autord.persistence.entity.OrderLibroEntity;
import com.cac.autord.persistence.repository.OrderLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderLibroService {


        private final OrderLibroRepository orderLibroRepository;

        @Autowired
        public OrderLibroService(OrderLibroRepository orderLibroRepository) {
            this.orderLibroRepository= orderLibroRepository;
        }


        //metodo que retornara una lista de todos los valores dentro de la tabla
        public List<OrderLibroEntity> getAll() {
            return this.orderLibroRepository.findAll();
        }


        //metodo que recupera un valor segun el id
        public OrderLibroEntity get(Long idItemCompra) {
            return this.orderLibroRepository.findById(idItemCompra).orElse(null);
        }

    //metodo para añadir o modificar un elemento de la tabla
    public OrderLibroEntity save(OrderLibroEntity orderLibro) {
        return this.orderLibroRepository.save(orderLibro);
    }

    //metodo para borrar un elemento de la tabla
    public void delete(Long id) {
        this.orderLibroRepository.deleteById(id);
    }
    //metodo que verifica si existe en la tabla el elemento con esa id
    public boolean exist(Long id) {
        return this.orderLibroRepository.existsById(id);
    }
}

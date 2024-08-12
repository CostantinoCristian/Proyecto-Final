package com.cac.autord.web.controller;
import com.cac.autord.persistence.entity.OrderEntity;
import com.cac.autord.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Este metodo recupera la lista de todos los elementos de la tabla
    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    //Este metodo recuperara un elemento segun su id
    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderEntity> get(@PathVariable Long idOrder){
        return ResponseEntity.ok(this.orderService.get(idOrder));
    }

    //metodo para crear un elemento(no permite modificar un metodo existente)
    @PostMapping
    public ResponseEntity<OrderEntity> add(@RequestBody OrderEntity order){
        if(order.getIdCompra() == null || !this.orderService.exist(order.getIdCompra())) {
            return ResponseEntity.ok(this.orderService.save(order));
        }
        return ResponseEntity.badRequest().build();
    }
    //metodo para modificar un elemento
    @PutMapping
    public ResponseEntity<OrderEntity> update(@RequestBody OrderEntity order){
        if (order.getIdCompra() != null && this.orderService.exist(order.getIdCompra())) {
            return ResponseEntity.ok(this.orderService.save(order));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para borrar un elemento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.orderService.exist(id)) {
            this.orderService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

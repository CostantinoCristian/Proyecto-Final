package com.cac.autord.web.controller;
import com.cac.autord.persistence.entity.OrderLibroEntity;
import com.cac.autord.service.OrderLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orderlibros")
public class OrderLibroController {
    private final OrderLibroService orderLibroService;

    @Autowired
    public OrderLibroController(OrderLibroService orderLibroService) {
        this.orderLibroService = orderLibroService;
    }

    //Este metodo recupera la lista de todos los elementos de la tabla
    @GetMapping
    public ResponseEntity<List<OrderLibroEntity>> getAll(){
        return ResponseEntity.ok(this.orderLibroService.getAll());
    }

    //Este metodo recuperara un elemento segun su id
    @GetMapping("/{idItemCompra}")
    public ResponseEntity<OrderLibroEntity> get(@PathVariable Long idItemCompra){
        return ResponseEntity.ok(this.orderLibroService.get(idItemCompra));
    }

    //metodo para crear un elemento(no permite modificar un metodo existente)
    @PostMapping
    public ResponseEntity<OrderLibroEntity> add(@RequestBody OrderLibroEntity orderLibro){
        if(orderLibro.getIdItemCompra() == null || !this.orderLibroService.exist(orderLibro.getIdItemCompra())) {
            return ResponseEntity.ok(this.orderLibroService.save(orderLibro));
        }
        return ResponseEntity.badRequest().build();
    }
    //metodo para modificar un elemento
    @PutMapping
    public ResponseEntity<OrderLibroEntity> update(@RequestBody OrderLibroEntity orderLibro){
        if (orderLibro.getIdItemCompra() != null && this.orderLibroService.exist(orderLibro.getIdItemCompra())) {
            return ResponseEntity.ok(this.orderLibroService.save(orderLibro));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para borrar un elemento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.orderLibroService.exist(id)) {
            this.orderLibroService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

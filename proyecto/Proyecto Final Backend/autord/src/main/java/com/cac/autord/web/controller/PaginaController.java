package com.cac.autord.web.controller;

import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.persistence.entity.PaginaEntity;
import com.cac.autord.service.LibroService;
import com.cac.autord.service.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paginas")
public class PaginaController {
    private final PaginaService paginaService;
    private final LibroService libroService;

    @Autowired
    public PaginaController(PaginaService paginaService, LibroService libroService) {
        this.paginaService = paginaService;
        this.libroService = libroService;
    }

    //Este metodo recupera la lista de todos los elementos de la tabla
    @GetMapping
    public ResponseEntity<List<PaginaEntity>> getAll(){
        return ResponseEntity.ok(this.paginaService.getAll());
    }

    //Este metodo recuperara un elemento segun su id
    @GetMapping(value="/{idPagina}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<PaginaEntity> get(@PathVariable Long idPagina){
        return ResponseEntity.ok(this.paginaService.get(idPagina));
    }
    //Este metodo recuperara un elemento segun su id
    @GetMapping(value="/libro/{idLibro}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<PaginaEntity>> getPaginasLibro(@PathVariable Long idLibro){
        LibroEntity libroEntity = libroService.getByid(idLibro);
        return ResponseEntity.ok(this.paginaService.getPaginasLibros(libroEntity));
    }

    //metodo para crear un elemento(no permite modificar un metodo existente)
    @PostMapping(value="/crear/{idLibro}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<PaginaEntity> add(@RequestBody PaginaEntity pagina, @PathVariable Long idLibro){
        if(pagina.getIdPagina() == null || !this.paginaService.exist(pagina.getIdPagina())) {
            return ResponseEntity.ok(this.paginaService.savePagina(pagina, idLibro));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para modificar un elemento
    @PutMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<PaginaEntity> update(@RequestBody PaginaEntity pagina){
        if (pagina.getIdPagina() != null && this.paginaService.exist(pagina.getIdPagina())) {
            return ResponseEntity.ok(this.paginaService.save(pagina));
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping(value="/update/{idLibro}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<PaginaEntity> update(@RequestBody PaginaEntity pagina, @PathVariable Long idLibro){
        if(pagina.getIdPagina() != null || this.paginaService.exist(pagina.getIdPagina())) {
            return ResponseEntity.ok(this.paginaService.savePagina(pagina, idLibro));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para borrar un elemento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.paginaService.exist(id)) {
            this.paginaService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

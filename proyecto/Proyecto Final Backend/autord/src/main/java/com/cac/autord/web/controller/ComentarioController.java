package com.cac.autord.web.controller;
import com.cac.autord.persistence.entity.ComentarioEntity;
import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.service.ComentarioService;
import com.cac.autord.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {
    private final ComentarioService comentarioService;
    private final LibroService libroService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService, LibroService libroService) {
        this.comentarioService = comentarioService;
        this.libroService = libroService;
    }

    //Este metodo recupera la lista de todos los elementos de la tabla
    @GetMapping
    public ResponseEntity<List<ComentarioEntity>> getAll(){
        return ResponseEntity.ok(this.comentarioService.getAll());
    }

    //Este metodo recuperara un elemento segun su id
    @GetMapping(value="/{idComentario}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ComentarioEntity> get(@PathVariable Long idComentario){
        return ResponseEntity.ok(this.comentarioService.get(idComentario));
    }
    //Este metodo recuperara un elemento segun su id
    @GetMapping(value="/libro/{idLibro}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<ComentarioEntity>> getComentariosLibro(@PathVariable Long idLibro){
        LibroEntity libroEntity = libroService.getByid(idLibro);
        return ResponseEntity.ok(this.comentarioService.getComentariosLibros(libroEntity));

    }

    //metodo para crear un elemento(no permite modificar un metodo existente)
    @PostMapping
    public ResponseEntity<ComentarioEntity> add(@RequestBody ComentarioEntity comentario){
        if(comentario.getIdComentario() == null || !this.comentarioService.exist(comentario.getIdComentario())) {
            return ResponseEntity.ok(comentarioService.save(comentario));
        }
        return ResponseEntity.badRequest().build();
    }
    //metodo para modificar un elemento
    @PutMapping
    public ResponseEntity<ComentarioEntity> update(@RequestBody ComentarioEntity comentario){
        if (comentario.getIdComentario() != null && this.comentarioService.exist(comentario.getIdComentario())) {
            return ResponseEntity.ok(this.comentarioService.save(comentario));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para borrar un elemento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.comentarioService.exist(id)) {
            this.comentarioService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

package com.cac.autord.web.controller;
import com.cac.autord.persistence.entity.ComentarioEntity;
import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.service.LibroService;
import com.cac.autord.service.TematicaService;
import com.cac.autord.persistence.entity.TematicaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tematicas")
public class TematicaController {
    private final TematicaService tematicaService;
    private final LibroService libroService;
    @Autowired
    public TematicaController(TematicaService tematicaService, LibroService libroService) {
        this.tematicaService = tematicaService;
        this.libroService = libroService;
    }

    //Este metodo recupera la lista de todos los elementos de la tabla
    @GetMapping
    public ResponseEntity<List<TematicaEntity>> getAll(){
        return ResponseEntity.ok(this.tematicaService.getAll());
    }

    //Este metodo recuperara un elemento segun su id
    @GetMapping("/{idTematica}")
    public ResponseEntity<TematicaEntity> get(@PathVariable Long idTematica){
        return ResponseEntity.ok(this.tematicaService.get(idTematica));
    }

    //Este metodo recuperara un tema con su tema
    @GetMapping("/tema/{tema}")
    public ResponseEntity<TematicaEntity> getByTema(@PathVariable String tema){
        return ResponseEntity.ok(this.tematicaService.getTema(tema));
    }
    //Este metodo recuperara un elemento segun su id
    @GetMapping(value="/libro/{idLibro}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String>getComentariosLibro(@PathVariable Long idLibro){
        LibroEntity libroEntity = libroService.getByid(idLibro);
        TematicaEntity tematicaEntity = this.tematicaService.getTematicaLibro(libroEntity);
        if (tematicaEntity != null) {
            return ResponseEntity.ok(tematicaEntity.getTema());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    //metodo para crear un elemento(no permite modificar un metodo existente)
    @PostMapping
    public ResponseEntity<TematicaEntity> add(@RequestBody TematicaEntity tematica){
        if(tematica.getIdTematica() == null || !this.tematicaService.exist(tematica.getIdTematica())) {
            return ResponseEntity.ok(this.tematicaService.save(tematica));
        }
        return ResponseEntity.badRequest().build();
    }
    //metodo para modificar un elemento
    @PutMapping
    public ResponseEntity<TematicaEntity> update(@RequestBody TematicaEntity tematica){
        if (tematica.getIdTematica() != null && this.tematicaService.exist(tematica.getIdTematica())) {
            return ResponseEntity.ok(this.tematicaService.save(tematica));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para borrar un elemento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.tematicaService.exist(id)) {
            this.tematicaService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

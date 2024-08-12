package com.cac.autord.web.controller;

import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.persistence.entity.UsuarioEntity;
import com.cac.autord.service.LibroService;
import com.cac.autord.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService libroService;
    private final UsuarioService usuarioService;

    @Autowired
    public LibroController(LibroService libroService, UsuarioService usuarioService) {
        this.libroService = libroService;
        this.usuarioService = usuarioService;
    }

    //Este metodo recupera la lista de todos los elementos de la tabla
    @GetMapping
    public ResponseEntity<List<LibroEntity>> getAll(){
        return ResponseEntity.ok(this.libroService.getAll());
    }

    //Este metodo recupera la lista de todos los elementos de la tabla paginados
    @GetMapping("/paginado")
    public ResponseEntity<Page<LibroEntity>> getAllPaginable(@RequestParam(defaultValue = "0") int pagina,
                                                             @RequestParam(defaultValue = "10") int elementos){
        return ResponseEntity.ok(this.libroService.getAllPaginable(pagina, elementos));
    }
    @GetMapping("/paginadoPublicado")
    public ResponseEntity<Page<LibroEntity>> getAllPublicatePaginable(@RequestParam(defaultValue = "0") int pagina,
                                                                      @RequestParam(defaultValue = "10") int elementos){
        return ResponseEntity.ok(this.libroService.getPublicadoTituloPaginado(pagina, elementos));
    }

    //Este metodo todos los libros que esten marcados como publicados
    @GetMapping("/publicados")
    public ResponseEntity<List<LibroEntity>> getPublicado(){
        return ResponseEntity.ok(this.libroService.getPublicadoTitulo());
    }

    //Este metodo recuperara un elemento segun su id
    @GetMapping(value="/{idLibro}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<LibroEntity> get(@PathVariable Long idLibro){
        return ResponseEntity.ok(this.libroService.getByid(idLibro));
    }

    //Este metodo recuperara un libro segun su nombre
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LibroEntity>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(this.libroService.getByTitulo(titulo));
    }
    //Este metodo recuperara un libro segun su nombre
    @GetMapping(value="/usuario/{idUsuario}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<LibroEntity>> getByTitulo(@PathVariable Long idUsuario){
        UsuarioEntity usuario = usuarioService.get(idUsuario);
        return ResponseEntity.ok(this.libroService.getByIdUsuario(usuario));
    }

    //metodo para crear un elemento(no permite modificar un metodo existente)
    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<LibroEntity> add(@RequestBody LibroEntity libro){
        if(libro.getIdLibro() == null || !this.libroService.exist(libro.getIdLibro())) {
            return ResponseEntity.ok(this.libroService.save(libro));
        }
        return ResponseEntity.badRequest().build();
    }
    //metodo para crear un elemento(no permite modificar un metodo existente)
    @PostMapping(value="/crear/{idUsuario}/{idTema}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<LibroEntity> add(@RequestBody LibroEntity libro,@PathVariable Long idUsuario, @PathVariable Long idTema){
        if(libro.getIdLibro() == null || !this.libroService.exist(libro.getIdLibro())) {
            return ResponseEntity.ok(this.libroService.saveLibro(libro, idUsuario, idTema));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para modificar un elemento
    @PutMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<LibroEntity> update(@RequestBody LibroEntity libro){
        if (libro.getIdLibro() != null && this.libroService.exist(libro.getIdLibro())) {
            return ResponseEntity.ok(this.libroService.save(libro));
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping(value="/update/{idUsuario}/{idTema}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<LibroEntity> update(@RequestBody LibroEntity libro,@PathVariable Long idUsuario, @PathVariable Long idTema){
        if(libro.getIdLibro() != null || this.libroService.exist(libro.getIdLibro())) {
            return ResponseEntity.ok(this.libroService.saveLibro(libro, idUsuario, idTema));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para borrar un elemento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.libroService.exist(id)) {
            this.libroService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

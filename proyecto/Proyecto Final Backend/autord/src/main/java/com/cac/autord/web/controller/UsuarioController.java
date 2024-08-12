package com.cac.autord.web.controller;

import com.cac.autord.persistence.entity.UsuarioEntity;
import com.cac.autord.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Este metodo recupera la lista de todos los elementos de la tabla
    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getAll(){
        return ResponseEntity.ok(this.usuarioService.getAll());
    }

    //Este metodo recuperara un elemento segun su id
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioEntity> get(@PathVariable Long idUsuario){
        return ResponseEntity.ok(this.usuarioService.get(idUsuario));
    }

    //Este metodo recuperara un usuario segun su nick y pass
    @GetMapping("/nick/{nick}/{pass}")
    public ResponseEntity<UsuarioEntity> getByNickOrPass(@PathVariable String nick, @PathVariable String pass) {
        UsuarioEntity usuario = this.usuarioService.getByNickAndPass(nick, pass);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    //Este metodo recuperara un usuario segun su nick y devuelve solo el nick
    @GetMapping("/nick/{nick}")
    public ResponseEntity<String> getByNick(@PathVariable String nick) {
        UsuarioEntity usuario = this.usuarioService.getByNick(nick);

        if (usuario != null) {
            return ResponseEntity.ok(usuario.getNick());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    //Este metodo recuperara un usuario segun su nick y devuelve solo el nick
    @GetMapping("/email/{email}")
    public ResponseEntity<String> getByEmail(@PathVariable String email) {
        UsuarioEntity usuario = this.usuarioService.getByEmail(email);

        if (usuario != null) {
            return ResponseEntity.ok(usuario.getEmail());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    //metodo para crear un elemento(no permite modificar un usuario existente)
    @PostMapping
    public ResponseEntity<UsuarioEntity> add(@RequestBody UsuarioEntity usuario){
        if(usuario.getIdUsuario() == null || !this.usuarioService.exist(usuario.getIdUsuario())) {
            return ResponseEntity.ok(this.usuarioService.save(usuario));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para modificar un elemento
    @PutMapping
    public ResponseEntity<UsuarioEntity> update(@RequestBody UsuarioEntity usuario){
        if (usuario.getIdUsuario() != null && this.usuarioService.exist(usuario.getIdUsuario())) {
            return ResponseEntity.ok(this.usuarioService.save(usuario));
        }
        return ResponseEntity.badRequest().build();
    }

    //metodo para borrar un elemento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.usuarioService.exist(id)) {
            this.usuarioService.delete(id);
            return ResponseEntity.ok().build();
            }
        return ResponseEntity.badRequest().build();
    }

}

package com.cac.autord.service;
import com.cac.autord.persistence.entity.UsuarioEntity;
import com.cac.autord.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository= usuarioRepository;
    }

    //metodo que retornara una lista de todos los valores dentro de la tabla
    public List<UsuarioEntity> getAll() {
        return this.usuarioRepository.findAll();
    }


    //metodo que recupera un valor segun el id
    public UsuarioEntity get(Long idUsuario) {
        return this.usuarioRepository.findById(idUsuario).orElse(null);
    }

    //busca un usuario por nick y contraseña
    public UsuarioEntity getByNickAndPass(String nick, String pass) {
        return this.usuarioRepository.findFirstByNickAndPass(nick, pass);
    }

    //busca un usuario por nick y contraseña
    public UsuarioEntity getByNick(String email) {
        return this.usuarioRepository.findFirstByNick(email);
    }
    //busca un usuario por email
    public UsuarioEntity getByEmail(String nick) {
        return this.usuarioRepository.findFirstByEmail(nick);
    }

    //metodo para añadir o modificar un elemento de la tabla
    public UsuarioEntity save(UsuarioEntity usuario) {
        return this.usuarioRepository.save(usuario);
    }

    //metodo para borrar un elemento de la tabla
    public void delete(Long id) {
        this.usuarioRepository.deleteById(id);
    }

    //metodo que verifica si existe en la tabla el elemento con esa id
    public boolean exist(Long id) {
        return this.usuarioRepository.existsById(id);
    }
}

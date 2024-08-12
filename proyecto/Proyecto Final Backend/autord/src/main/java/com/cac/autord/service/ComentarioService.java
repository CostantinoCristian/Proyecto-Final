package com.cac.autord.service;
import com.cac.autord.persistence.entity.ComentarioEntity;
import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.persistence.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }


    //metodo que retornara una lista de todos los valores dentro de la tabla
    public List<ComentarioEntity> getAll() {
        return this.comentarioRepository.findAll();
    }


    //metodo que recupera un valor segun el id
    public ComentarioEntity get(Long idComentario) {
        return this.comentarioRepository.findById(idComentario).orElse(null);
    }

    //metodo para añadir o modificar un elemento de la tabla
    public ComentarioEntity save(ComentarioEntity comentario) {
        return this.comentarioRepository.save(comentario);
    }

    //metodo para borrar un elemento de la tabla
    public void delete(Long id) {
        this.comentarioRepository.deleteById(id);
    }

    //metodo que verifica si existe en la tabla el elemento con esa id
    public boolean exist(Long id) {
        return this.comentarioRepository.existsById(id);
    }

    //encuentra los comentarios de un libro especifico
    public List<ComentarioEntity> getComentariosLibros(LibroEntity idLibro) {
        return this.comentarioRepository.findAllByIdLibroOrderByFechaPublicacionDesc(idLibro);
    }
}


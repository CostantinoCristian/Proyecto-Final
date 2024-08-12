package com.cac.autord.service;
import com.cac.autord.persistence.entity.ComentarioEntity;
import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.persistence.entity.TematicaEntity;
import com.cac.autord.persistence.repository.TematicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TematicaService {

    private final TematicaRepository tematicaRepository;

    @Autowired
    public TematicaService(TematicaRepository tematicaRepository) {
        this.tematicaRepository= tematicaRepository;
    }

    //metodo que retornara una lista de todos los valores dentro de la tabla tematica con sus correspondiente ID
    public List<TematicaEntity> getAll() {
        return this.tematicaRepository.findAll();
    }

    //metodo que recupera un valor segun el idTematica
    public TematicaEntity get(Long idTematica) {
        return this.tematicaRepository.findById(idTematica).orElse(null);
    }

    //metodo que recupera un valor segun el tema
    public TematicaEntity getTema(String tema) {
        return this.tematicaRepository.findFirstByTemaIgnoreCase(tema);
    }


    //metodo para añadir o modificar un elemento de la tabla
    public TematicaEntity save(TematicaEntity tematica) {
        return this.tematicaRepository.save(tematica);
    }

    //metodo para borrar un elemento de la tabla
    public void delete(Long id) {
        this.tematicaRepository.deleteById(id);
    }

    //metodo que verifica si existe en la tabla el elemento con esa id
    public boolean exist(Long id) {
        return this.tematicaRepository.existsById(id);
    }

    //encuentra la tematica de un libro especifico
    public TematicaEntity getTematicaLibro(LibroEntity idLibro) {
        return this.tematicaRepository.findFirstByLibrosTematica(idLibro);
    }
}

package com.cac.autord.service;
import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.persistence.entity.PaginaEntity;
import com.cac.autord.persistence.repository.PaginaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaginaService {

    private final PaginaRepository paginaRepository;
    private final LibroService libroService;

    @Autowired
    public PaginaService(PaginaRepository paginaRepository, LibroService libroService) {
        this.paginaRepository= paginaRepository;
        this.libroService = libroService;
    }


    //metodo que retornara una lista de todos los valores dentro de la tabla
    public List<PaginaEntity> getAll() {
        return this.paginaRepository.findAll();
    }


    //metodo que recupera un valor segun el id
    public PaginaEntity get(Long idPagina) {
        return this.paginaRepository.findById(idPagina).orElse(null);
    }

    //metodo para añadir o modificar un elemento de la tabla
    public PaginaEntity save(PaginaEntity pagina) {
        return this.paginaRepository.save(pagina);
    }

    //metodo para borrar un elemento de la tabla
    public void delete(Long id) {
        this.paginaRepository.deleteById(id);
    }
    //metodo que verifica si existe en la tabla el elemento con esa id
    public boolean exist(Long id) {
        return this.paginaRepository.existsById(id);
    }

    //encuentra los comentarios de un libro especifico
    public List<PaginaEntity> getPaginasLibros(LibroEntity idLibro) {
        return this.paginaRepository.findAllByIdLibroOrderByNumPagina(idLibro);
    }

    //Crea un elemento pagina para un libro en especifico
    public PaginaEntity savePagina(PaginaEntity pagina, Long idLibro) {
        LibroEntity libro = libroService.getByid(idLibro);
        pagina.setIdLibro(libro);
        return this.paginaRepository.save(pagina);
    }
}
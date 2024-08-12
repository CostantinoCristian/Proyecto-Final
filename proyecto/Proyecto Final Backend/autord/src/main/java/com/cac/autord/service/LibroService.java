package com.cac.autord.service;
import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.persistence.entity.TematicaEntity;
import com.cac.autord.persistence.entity.UsuarioEntity;
import com.cac.autord.persistence.repository.LibroPageSortRepository;
import com.cac.autord.persistence.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final LibroPageSortRepository libroPageSortRepository; //para trabajar con mucha cantidad de datos y que no recupere el 100% de los datos en cada consulta
    private final TematicaService tematicaService;
    private final UsuarioService usuarioService;
    @Autowired
    public LibroService(LibroRepository libroRepository, LibroPageSortRepository libroPageSortRepository, TematicaService tematicaService, UsuarioService usuarioService) {
        this.libroRepository= libroRepository;
        this.libroPageSortRepository = libroPageSortRepository;
        this.tematicaService = tematicaService;
        this.usuarioService = usuarioService;
    }


    //metodo que retornara una lista de todos los valores dentro de la tabla
    public List<LibroEntity> getAll() {
        return this.libroRepository.findAll();
    }

    //metodo que retornara una lista de todos los valores dentro de la tabla paginados
    public Page<LibroEntity> getAllPaginable(int pagina, int elementos) {
        Pageable pageRequest = PageRequest.of(pagina, elementos);
        return this.libroPageSortRepository.findAll(pageRequest);
    }

    //muestra los libros publicados organizados por titulo
    public List<LibroEntity> getPublicadoTitulo() {
        return this.libroRepository.findAllBypublicadoTrueOrderByTitulo();
    }

    //muestra los libros publicados organizados por titulo
    public Page<LibroEntity> getPublicadoTituloPaginado(int pagina, int elementos) {
        Pageable pageRequest = PageRequest.of(pagina, elementos);
        return this.libroPageSortRepository.findAllByPublicadoTrueOrderByTitulo(pageRequest);
    }

    //busca un libro por titulo
    public  List<LibroEntity> getByTitulo(String titulo) {
        return this.libroRepository.findAllByPublicadoTrueAndTituloContainingIgnoreCase(titulo);
    }

    //busca libros por idUsuario
    public  List<LibroEntity> getByIdUsuario(UsuarioEntity usuario) {
        return this.libroRepository.findAllByIdUsuario(usuario);
    }

    //metodo que recupera un valor segun el id
    public LibroEntity getByid(Long idLibro) {
        return this.libroRepository.findById(idLibro).orElse(null);
    }

    //metodo para añadir o modificar un elemento de la tabla
    public LibroEntity save(LibroEntity libro) {
        return this.libroRepository.save(libro);
    }

    //metodo para añadir o modificar un elemento de la tabla
    public LibroEntity saveLibro(LibroEntity libro, Long idUsuario, Long idTema) {
        UsuarioEntity usuario = usuarioService.get(idUsuario);
        TematicaEntity tema = tematicaService.get(idTema);
        libro.setIdUsuario(usuario);
        libro.setIdTematica(tema);
        return this.libroRepository.save(libro);
    }

    //metodo para borrar un elemento de la tabla
    public void delete(Long id) {
        this.libroRepository.deleteById(id);
    }
    //metodo que verifica si existe en la tabla el elemento con esa id
    public boolean exist(Long id) {
        return this.libroRepository.existsById(id);
    }
}

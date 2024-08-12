package com.cac.autord.persistence.repository;

import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface LibroRepository extends ListCrudRepository<LibroEntity, Long> {
    List<LibroEntity> findAllBypublicadoTrueOrderByTitulo();
    List<LibroEntity> findAllByPublicadoTrueAndTituloContainingIgnoreCase(String titulo);
    List<LibroEntity> findAllByIdUsuario(UsuarioEntity usuario);
}

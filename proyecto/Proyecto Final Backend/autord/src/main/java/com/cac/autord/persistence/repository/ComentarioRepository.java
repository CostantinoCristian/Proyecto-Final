package com.cac.autord.persistence.repository;

import com.cac.autord.persistence.entity.ComentarioEntity;

import com.cac.autord.persistence.entity.LibroEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;


public interface ComentarioRepository extends ListCrudRepository<ComentarioEntity, Long> {
    List<ComentarioEntity> findAllByIdLibroOrderByFechaPublicacionDesc(LibroEntity idLibro);
}

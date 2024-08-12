package com.cac.autord.persistence.repository;

import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.persistence.entity.TematicaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TematicaRepository extends ListCrudRepository<TematicaEntity, Long> {
    TematicaEntity findFirstByTemaIgnoreCase(String tema);
    TematicaEntity findFirstByLibrosTematica(LibroEntity idLibro);
}

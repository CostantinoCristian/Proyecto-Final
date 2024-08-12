package com.cac.autord.persistence.repository;
import com.cac.autord.persistence.entity.LibroEntity;
import com.cac.autord.persistence.entity.PaginaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PaginaRepository extends ListCrudRepository<PaginaEntity, Long> {
    List<PaginaEntity> findAllByIdLibroOrderByNumPagina(LibroEntity idLibro);
}

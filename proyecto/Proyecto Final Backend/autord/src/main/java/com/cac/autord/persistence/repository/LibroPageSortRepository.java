package com.cac.autord.persistence.repository;

import com.cac.autord.persistence.entity.LibroEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface LibroPageSortRepository extends ListPagingAndSortingRepository<LibroEntity, Long> {
    //Metodos de paginacion para tratar grandes cantidades de datos, y reducir la cantidad de datos que se obtienen a la vez
    Page<LibroEntity> findAllByPublicadoTrueOrderByTitulo(Pageable pageable);
}

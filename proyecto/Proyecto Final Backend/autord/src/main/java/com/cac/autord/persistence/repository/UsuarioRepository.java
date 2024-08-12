package com.cac.autord.persistence.repository;

import com.cac.autord.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UsuarioRepository extends ListCrudRepository<UsuarioEntity, Long> {
    UsuarioEntity findFirstByNickAndPass(String nick, String pass);
    UsuarioEntity findFirstByEmail(String email);
    UsuarioEntity findFirstByNick(String nick);
}

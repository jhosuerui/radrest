package com.accounting.radrest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.radrest.model.AppUser;

/**
 * Repositorio JPA para la entidad AppUser.
 * Proporciona operaciones CRUD y métodos de búsqueda personalizados.
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

    /**
     * Busca un usuario por su nombre de usuario.
     * @param username El nombre de usuario.
     * @return Un Optional con el usuario si se encuentra.
     */
    Optional<AppUser> findByUsername(String username);

    /**
     * Busca un usuario por su identificación gubernamental (govId).
     * @param govId El identificador único del gobierno.
     * @return Un Optional con el usuario si se encuentra.
     */
    Optional<AppUser> findByGovId(String govId);

    
}

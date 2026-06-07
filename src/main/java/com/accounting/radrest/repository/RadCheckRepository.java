package com.accounting.radrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accounting.radrest.model.RadCheck;

import jakarta.transaction.Transactional;

/**
 * Repositorio JPA para la entidad RadCheck.
 * Encargado de la validación y gestión de credenciales de acceso de FreeRADIUS.
 * Alineado con la sección 7.2 de la especificación de arquitectura.
 */
@Repository
public interface RadCheckRepository extends JpaRepository<RadCheck, Integer> {

    /**
     * Recupera todos los atributos/reglas de control asociados a un usuario.
     * Aprovecha el índice 'radcheck_username_idx' definido en la entidad.
     * * @param username El nombre de usuario.
     * @return Lista de registros RadCheck para ese usuario.
     */
    List<RadCheck> findByUsername(String username);

    /**
     * Busca un registro específico de un usuario por su tipo de atributo (ej. 'Cleartext-Password' o 'Auth-Type').
     * * @param username El nombre de usuario.
     * @param attribute El nombre del atributo de FreeRADIUS.
     * @return Un Optional con el registro encontrado.
     */
    Optional<RadCheck> findByUsernameAndAttribute(String username, String attribute);

    /**
     * Lógica 'isEnable' basada en la especificación del modelo:
     * Verifica que el usuario NO tenga un atributo de rechazo explícito ('Auth-Type' con valor 'Reject').
     * * @param username El nombre de usuario a verificar.
     * @return true si el usuario está habilitado para autenticar, false si tiene una regla de rechazo.
     */
    default boolean isEnable(String username) {
        return findByUsernameAndAttribute(username, "Auth-Type")
                .map(radCheck -> !"Reject".equalsIgnoreCase(radCheck.getValue()))
                .orElse(true); // Si no tiene el atributo Auth-Type, asumimos que está habilitado por defecto
    }

    /**
     * Implementa la desactivación segura de la cuenta sin borrar el historial.
     * Cambia el atributo del usuario a 'Auth-Type' y su valor a 'Reject' con el operador ':='.
     * Si el registro 'Auth-Type' no existe para el usuario, se debe insertar/crear en la capa de Servicio.
     * * @param username El nombre de usuario a rechazar de forma regulatoria.
     * @return Número de registros actualizados.
     */
    @Modifying
    @Transactional
    @Query("UPDATE RadCheck r SET r.attribute = 'Auth-Type', r.op = ':=', r.value = 'Reject' " +
           "WHERE r.username = :username AND r.attribute = 'Cleartext-Password'")
    int rejectUserAccess(@Param("username") String username);
}
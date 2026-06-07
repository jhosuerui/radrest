package com.accounting.radrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accounting.radrest.model.RadAcct;

/**
 * Repositorio JPA para la entidad RadAcct.
 * Maneja el registro de contabilidad de sesiones de red (AAA) nativo de FreeRADIUS.
 * Alineado con la sección 7.6 de la especificación de arquitectura.
 */
@Repository
public interface RadAcctRepository extends JpaRepository<RadAcct, Long> {

    /**
     * Busca una sesión específica por su identificador único global (acctUniqueId).
     * @param acctUniqueId El identificador único generado por FreeRADIUS.
     * @return Un Optional con la sesión de contabilidad.
     */
    Optional<RadAcct> findByAcctUniqueId(String acctUniqueId);

    /**
     * Encuentra todas las sesiones activas actuales de un usuario.
     * Una sesión está activa si 'acctStopTime' es nulo.
     * Aprovecha el índice 'radacct_active_session_idx' definido en la entidad.
     * * @param username Nombre del usuario.
     * @return Lista de sesiones de contabilidad activas.
     */
    List<RadAcct> findByUsernameAndAcctStopTimeIsNull(String username);

    /**
     * Obtiene el consumo total de datos (de subida y bajada) de un usuario.
     * * @param username Nombre del usuario.
     * @return Un arreglo de objetos con [0] = totalInputOctets, [1] = totalOutputOctets.
     */
    @Query("SELECT SUM(r.acctInputOctets), SUM(r.acctOutputOctets) FROM RadAcct r WHERE r.username = :username")
    Object[] getTotalOctetsByUsername(@Param("username") String username);

    /**
     * Lógica 'isEnable' adaptada al contexto de sesiones:
     * Verifica si el usuario tiene actualmente alguna sesión de red activa.
     * * @param username El nombre de usuario a consultar.
     * @return true si el usuario tiene al menos una conexión abierta (acctStopTime es NULL), false en caso contrario.
     */
    default boolean isEnable(String username) {
        return !findByUsernameAndAcctStopTimeIsNull(username).isEmpty();
    }
}
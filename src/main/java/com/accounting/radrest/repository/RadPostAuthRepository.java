package com.accounting.radrest.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.radrest.model.RadPostAuth;

/**
 * Repositorio JPA para la entidad RadPostAuth.
 * Encargado de la auditoría de todos los intentos de acceso (aceptados y rechazados) en FreeRADIUS.
 * Alineado con la sección 7.7 de la especificación de arquitectura.
 */

@Repository
public interface RadPostAuthRepository extends JpaRepository<RadPostAuth, Long> {

    /**
     * Recupera el historial completo de intentos de acceso de un usuario.
     * Saca provecho del índice 'radpostauth_username_idx' definido en la entidad.
     * * @param username El nombre de usuario.
     * @return Lista de registros de autenticación del usuario.
     */
    List<RadPostAuth> findByUsername(String username);

    /**
     * Encuentra los intentos de autenticación de un usuario filtrando por una respuesta específica (ej. 'Access-Reject').
     * Útil para detectar ataques de fuerza bruta o bloqueos recurrentes.
     * * @param username El nombre de usuario.
     * @param reply El tipo de respuesta de FreeRADIUS ('Access-Accept', 'Access-Reject').
     * @return Lista de registros coincidentes.
     */
    List<RadPostAuth> findByUsernameAndReply(String username, String reply);

    /**
     * Recupera los registros de autenticación ocurridos después de una fecha y hora específicas.
     * * @param authDate Fecha y hora de corte (con zona horaria).
     * @return Lista de intentos de acceso recientes.
     */
    List<RadPostAuth> findByAuthDateAfter(OffsetDateTime authDate);

    /**
     * Lógica 'isEnable' adaptada al contexto de auditoría pos-autenticación:
     * Verifica si el último intento de acceso registrado para el usuario NO fue un rechazo ('Access-Reject').
     * * @param username El nombre de usuario a verificar.
     * @return true si su último intento de conexión fue exitoso o no tiene registros, 
     * false si el último intento fue rechazado por el servidor AAA.
     */
    default boolean isEnable(String username) {
        // Obtenemos el intento de autenticación más reciente
        return findByUsername(username).stream()
                .max((a, b) -> a.getAuthDate().compareTo(b.getAuthDate()))
                .map(lastAuth -> !"Access-Reject".equalsIgnoreCase(lastAuth.getReply()))
                .orElse(true); // Si no hay historial, se asume habilitado por defecto
    }
}
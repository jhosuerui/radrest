package com.accounting.radrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.radrest.model.RadReply;

/**
 * Repositorio JPA para la entidad RadReply.
 * Administra los atributos de respuesta y autorización individualizados enviados al NAS por FreeRADIUS.
 * Alineado con la sección 7.3 de la especificación de arquitectura.
 */
@Repository
public interface RadReplyRepository extends JpaRepository<RadReply, Integer> {

    /**
     * Recupera todas las reglas de respuesta y autorización asignadas a un usuario específico.
     * Saca el máximo provecho del índice 'radreply_username_idx' definido en la entidad.
     * * @param username El nombre de usuario.
     * @return Lista de atributos de respuesta RadReply del usuario.
     */
    List<RadReply> findByUsername(String username);

    /**
     * Busca un atributo de respuesta específico asignado a un usuario concreto (ej. 'Framed-IP-Address').
     * * @param username El nombre de usuario.
     * @param attribute El atributo de FreeRADIUS.
     * @return Un Optional con el registro si se encuentra configurado para el usuario.
     */
    Optional<RadReply> findByUsernameAndAttribute(String username, String attribute);

    /**
     * Lógica 'isEnable' adaptada al contexto de respuestas de autorización individuales:
     * Al igual que en la configuración de grupos, una manera común de deshabilitar temporalmente 
     * el procesamiento de las respuestas individuales de un usuario sin borrarlas de la base de datos 
     * es definiendo el atributo de control 'Fall-Through' con el valor 'No'.
     * * @param username El nombre de usuario a verificar.
     * @return true si los atributos de respuesta del usuario están activos, false si están ignorados mediante Fall-Through.
     */
    default boolean isEnable(String username) {
        return findByUsernameAndAttribute(username, "Fall-Through")
                .map(radReply -> !"No".equalsIgnoreCase(radReply.getValue()))
                .orElse(true); // Si no existe el atributo de control, se asume que las respuestas están habilitadas
    }
}

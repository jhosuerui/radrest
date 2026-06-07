package com.accounting.radrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounting.radrest.model.RadGroupReply;

/**
 * Repositorio JPA para la entidad RadGroupReply.
 * Administra los atributos de respuesta/retorno que se envían al NAS a nivel de grupo en FreeRADIUS.
 * Alineado con la sección 7.4 de la especificación de arquitectura.
 */

public interface RadGroupReplyRepository extends JpaRepository<RadGroupReply, Integer> {

    /**
     * Recupera todos los atributos de respuesta asociados a un grupo específico.
     * Saca provecho del índice 'radgroupreply_groupname_idx' definido en la entidad.
     * * @param groupName El nombre del grupo.
     * @return Lista de atributos RadGroupReply del grupo.
     */
    List<RadGroupReply> findByGroupName(String groupName);

    /**
     * Busca un atributo de respuesta específico dentro de un grupo (ej. 'Fall-Through').
     * * @param groupName El nombre del grupo.
     * @param attribute El atributo de FreeRADIUS.
     * @return Un Optional con el atributo grupal si se encuentra.
     */
    Optional<RadGroupReply> findByGroupNameAndAttribute(String groupName, String attribute);

    /**
     * Lógica 'isEnable' adaptada a la tabla de respuestas grupales:
     * En las tablas Reply, una manera común de deshabilitar dinámicamente un conjunto de respuestas 
     * sin borrarlas es usando el atributo de control interno de FreeRADIUS 'Fall-Through' configurado en 'No'.
     * * @param groupName El nombre del grupo a verificar.
     * @return true si el grupo procesa sus respuestas normalmente, false si tiene el Fall-Through bloqueado.
     */
    default boolean isEnable(String groupName) {
        return findByGroupNameAndAttribute(groupName, "Fall-Through")
                .map(groupReply -> !"No".equalsIgnoreCase(groupReply.getValue()))
                .orElse(true); // Si no existe el atributo Fall-Through, las respuestas están activas por defecto
    }
}
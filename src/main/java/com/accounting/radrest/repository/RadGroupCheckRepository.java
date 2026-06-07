package com.accounting.radrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounting.radrest.model.RadGroupCheck;

/**
 * Repositorio JPA para la entidad RadGroupCheck.
 * Administra las condiciones de validación aplicadas a nivel de grupo en FreeRADIUS.
 * Alineado con la sección 7.4 de la especificación de arquitectura.
 */

public interface RadGroupCheckRepository extends JpaRepository<RadGroupCheck, Integer> {

    /**
     * Recupera todas las reglas de validación asociadas a un grupo específico.
     * Saca provecho del índice 'radgroupcheck_groupname_idx' definido en la entidad.
     * * @param groupName El nombre del grupo.
     * @return Lista de condiciones RadGroupCheck del grupo.
     */
    List<RadGroupCheck> findByGroupName(String groupName);

    /**
     * Busca una condición específica dentro de un grupo filtrando por su atributo.
     * * @param groupName El nombre del grupo.
     * @param attribute El atributo de FreeRADIUS (ej. 'Auth-Type').
     * @return Un Optional con la regla grupal si se encuentra.
     */
    Optional<RadGroupCheck> findByGroupNameAndAttribute(String groupName, String attribute);

    /**
     * Lógica 'isEnable' adaptada al contexto de políticas grupales:
     * Verifica que el grupo NO cuente con una regla explícita de rechazo global
     * ('Auth-Type' con valor 'Reject').
     * * @param groupName El nombre del grupo a verificar.
     * @return true si el grupo está operativo, false si tiene una política de bloqueo masivo.
     */
    default boolean isEnable(String groupName) {
        return findByGroupNameAndAttribute(groupName, "Auth-Type")
                .map(groupCheck -> !"Reject".equalsIgnoreCase(groupCheck.getValue()))
                .orElse(true); // Si el grupo no tiene la restricción Auth-Type, está habilitado por defecto
    }
}
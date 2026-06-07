package com.accounting.radrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.radrest.model.RadUserGroup;

/**
 * Repositorio JPA para la entidad RadUserGroup.
 * Encargado de la asignación y jerarquía de usuarios dentro de grupos de políticas en FreeRADIUS.
 * Alineado con la sección 7.4 de la especificación de arquitectura.
 */
@Repository
public interface RadUserGroupRepository extends JpaRepository<RadUserGroup, Integer> {

    /**
     * Recupera todas las asignaciones de grupo de un usuario en particular,
     * ordenadas por prioridad de forma ascendente (menor número indica mayor prioridad).
     * Saca el máximo provecho del índice 'radusergroup_username_idx' definido en la entidad.
     * * @param username El nombre de usuario.
     * @return Lista de grupos ordenados por prioridad.
     */
    List<RadUserGroup> findByUsernameOrderByPriorityAsc(String username);

    /**
     * Encuentra todos los usuarios que pertenecen a un grupo específico.
     * * @param groupName El nombre del grupo de políticas.
     * @return Lista de registros de asignación para ese grupo.
     */
    List<RadUserGroup> findByGroupName(String groupName);

    /**
     * Busca la asignación específica de un usuario a un grupo concreto.
     * * @param username El nombre de usuario.
     * @param groupName El nombre del grupo.
     * @return Un Optional con el registro si la relación existe.
     */
    Optional<RadUserGroup> findByUsernameAndGroupName(String username, String groupName);

    /**
     * Lógica 'isEnable' adaptada al mapeo de membresías:
     * Verifica si el usuario cuenta con al menos una asignación de grupo activa en el sistema.
     * En FreeRADIUS, un usuario sin grupo asignado suele quedar fuera de las políticas de acceso globales.
     * * @param username El nombre de usuario a verificar.
     * @return true si el usuario está asociado a algún grupo de políticas, false en caso contrario.
     */
    default boolean isEnable(String username) {
        return !findByUsernameOrderByPriorityAsc(username).isEmpty();
    }
}
package com.accounting.radrest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.radrest.model.Nas;

/**
 * Repositorio JPA para la entidad Nas.
 * Administra el inventario de Servidores de Acceso a la Red (NAS) nativos de FreeRADIUS.
 * Alineado con la sección 7.5 de la especificación de arquitectura.
 */
@Repository
public interface NasRepository extends JpaRepository<Nas, Integer> {

    /**
     * Busca un servidor NAS por su identificador de red único (habitualmente su IP o DNS).
     * Saca provecho del índice 'nas_nasname_idx' definido en la entidad.
     * * @param nasName Dirección IP o nombre de host del NAS.
     * @return Un Optional con el servidor NAS si existe.
     */
    Optional<Nas> findByNasName(String nasName);

    /**
     * Busca un servidor NAS por su nombre corto identificativo.
     * * @param shortName Nombre corto asignado al NAS.
     * @return Un Optional con el servidor NAS si existe.
     */
    Optional<Nas> findByShortName(String shortName);

    /**
     * Lógica 'isEnable' adaptada al inventario de hardware de FreeRADIUS:
     * Comprueba si el servidor NAS se encuentra actualmente registrado y activo en el sistema.
     * * @param nasName Dirección IP o identificador de red del NAS a verificar.
     * @return true si el NAS está registrado en la tabla, false en caso contrario.
     */
    default boolean isEnable(String nasName) {
        return findByNasName(nasName).isPresent();
    }
}
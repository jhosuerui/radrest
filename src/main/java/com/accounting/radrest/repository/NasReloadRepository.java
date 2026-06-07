package com.accounting.radrest.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.radrest.model.NasReload;

/**
 * Repositorio JPA para la entidad NasReload.
 * Maneja la persistencia de los registros de sincronización y recarga de los NAS de FreeRADIUS.
 * Alineado con la sección 7.8 de la especificación de arquitectura.
 */
@Repository
public interface NasReloadRepository extends JpaRepository<NasReload, String>{

    /**
     * Busca los registros de recarga que ocurrieron después de una fecha/hora específica.
     * Útil para auditorías o para verificar recargas recientes en el cluster de FreeRADIUS.
     * * @param time Fecha y hora de corte (con zona horaria).
     * @return Lista de registros de recarga posteriores a la fecha proporcionada.
     */
    List<NasReload> findByReloadTimeAfter(OffsetDateTime time);

    /**
     * Verifica si un NAS específico (por su IP) ha sido recargado.
     * Como 'nasIpAddress' es el @Id, este método aprovecha el tipado estricto de Spring Data.
     * * @param nasIpAddress La dirección IP del NAS (mapeada como tipo inet en BD).
     * @return true si el NAS existe en la tabla de recargas, false en caso contrario.
     */
    default boolean isEnable(String nasIpAddress) {
        return existsById(nasIpAddress);
    }

}

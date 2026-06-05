package com.accounting.radrest.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad nativa de FreeRADIUS para registrar la sincronización y recarga de los NAS.
 * Alineado con la sección 7.8 de la especificación de arquitectura.
 */

@Data
@Entity
@Table(name = "nasreload")
public class NasReload {

    @Id
    @Column(name = "nasipaddress", nullable = false, columnDefinition = "inet")
    private String nasIpAddress;

    @Column(name = "reloadtime", nullable = false, columnDefinition = "timestamp with time zone")
    private OffsetDateTime reloadTime;
}
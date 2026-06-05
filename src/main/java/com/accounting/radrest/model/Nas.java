package com.accounting.radrest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad nativa de FreeRADIUS para el inventario de Servidores de Acceso a la Red (NAS).
 * Alineado con la sección 7.5 de la especificación de arquitectura.
 */

@Data
@Entity
@Table(name = "nas", indexes = {
    @Index(name = "nas_nasname_idx", columnList = "nasname")
})
public class Nas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nasname", nullable = false, columnDefinition = "text")
    private String nasName;

    @Column(name = "shortname", nullable = false, columnDefinition = "text")
    private String shortName;

    @Column(name = "type", nullable = false, columnDefinition = "text")
    private String type;

    @Column(name = "ports")
    private Integer ports;

    @Column(name = "secret", nullable = false, columnDefinition = "text")
    private String secret;

    @Column(name = "server", columnDefinition = "text")
    private String server;

    @Column(name = "community", columnDefinition = "text")
    private String community;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
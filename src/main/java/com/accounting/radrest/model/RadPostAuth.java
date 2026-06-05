package com.accounting.radrest.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad nativa de FreeRADIUS para la auditoría de intentos de acceso.
 * Alineado con la sección 7.7 de la especificación de arquitectura.
 */

@Data
@Entity
@Table(name = "radpostauth", indexes = {
    @Index(name = "radpostauth_username_idx", columnList = "username"),
    @Index(name = "radpostauth_class_idx", columnList = "class")
})
public class RadPostAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, columnDefinition = "text")
    private String username;

    @Column(name = "pass", columnDefinition = "text")
    private String pass;

    @Column(name = "reply", columnDefinition = "text")
    private String reply;

    @Column(name = "calledstationid", columnDefinition = "text")
    private String calledStationId;

    @Column(name = "callingstationid", columnDefinition = "text")
    private String callingStationId;

    @Column(name = "authdate", nullable = false, columnDefinition = "timestamp with time zone")
    private OffsetDateTime authDate;

    @Column(name = "class", columnDefinition = "text")
    private String clazz; // 'class' es palabra reservada en Java
}
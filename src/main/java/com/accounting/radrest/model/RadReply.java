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
 * Entidad nativa de FreeRADIUS para la autorización individual de usuarios.
 * Alineado con la sección 7.3 de la especificación de arquitectura.
 */

@Data
@Entity
@Table(name = "radreply", indexes = {
    @Index(name = "radreply_username_idx", columnList = "username, attribute")
})
public class RadReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, columnDefinition = "text")
    private String username;

    @Column(name = "attribute", nullable = false, columnDefinition = "text")
    private String attribute;

    @Column(name = "op", nullable = false, length = 2)
    private String op;

    @Column(name = "value", nullable = false, columnDefinition = "text")
    private String value;
}
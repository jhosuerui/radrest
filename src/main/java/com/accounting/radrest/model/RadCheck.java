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
 * Entidad nativa de FreeRADIUS para la validación de credenciales de acceso.
 * Alineado con la sección 7.2 de la especificación de arquitectura.
 */

@Data
@Entity
@Table(name = "radcheck", indexes = {
        @Index(name = "radcheck_username_idx", columnList = "username, attribute")
})
public class RadCheck {

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
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
 * Entidad nativa de FreeRADIUS para relacionar usuarios con grupos de políticas.
 * Alineado con la sección 7.4 de la especificación de arquitectura.
 */

@Data
@Entity
@Table(name = "radusergroup", indexes = {
    @Index(name = "radusergroup_username_idx", columnList = "username")
})
public class RadUserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, columnDefinition = "text")
    private String username;

    @Column(name = "groupname", nullable = false, columnDefinition = "text")
    private String groupName;

    @Column(name = "priority", nullable = false)
    private Integer priority;
}
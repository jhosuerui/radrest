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
 * Entidad nativa de FreeRADIUS para los atributos de retorno grupales.
 * Alineado con la sección 7.4 de la especificación de arquitectura.
 */

@Data
@Entity
@Table(name = "radgroupreply", indexes = {
    @Index(name = "radgroupreply_groupname_idx", columnList = "groupname, attribute")
})
public class RadGroupReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "groupname", nullable = false, columnDefinition = "text")
    private String groupName;

    @Column(name = "attribute", nullable = false, columnDefinition = "text")
    private String attribute;

    @Column(name = "op", nullable = false, length = 2)
    private String op;

    @Column(name = "value", nullable = false, columnDefinition = "text")
    private String value;
}
package com.accounting.radrest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nas", indexes = {
    @Index(name = "nas_nasname_idx", columnList = "nasname")
})
@Getter
@Setter
public class Nas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "nasname", columnDefinition = "text", nullable = false)
    private String nasName;

    @NotBlank
    @Column(name = "shortname", columnDefinition = "text", nullable = false)
    private String shortName;

    @NotBlank
    @Column(name = "type", columnDefinition = "text", nullable = false)
    private String type = "other";

    @Column(name = "ports")
    private Integer ports;

    @NotBlank
    @Column(name = "secret", columnDefinition = "text", nullable = false)
    private String secret;

    @Column(name = "server", columnDefinition = "text")
    private String server;

    @Column(name = "community", columnDefinition = "text")
    private String community;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}

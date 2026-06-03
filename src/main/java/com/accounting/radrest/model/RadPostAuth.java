package com.accounting.radrest.model;

import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;

@Entity
@Table(name = "radpostauth", indexes = {
    @Index(name = "radpostauth_username_idx", columnList = "username"),
    @Index(name = "radpostauth_class_idx", columnList = "class")
})
@Getter
@Setter
public class RadPostAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "username", columnDefinition = "text", nullable = false)
    private String username;

    @Column(name = "pass", columnDefinition = "text")
    private String pass;

    @Column(name = "reply", columnDefinition = "text")
    private String reply;

    @Column(name = "calledstationid", columnDefinition = "text")
    private String calledStationId;

    @Column(name = "callingstationid", columnDefinition = "text")
    private String callingStationId;

    @NotNull
    @Column(name = "authdate", nullable = false, updatable = false)
    private OffsetDateTime authDate = OffsetDateTime.now();

    @Column(name = "class", columnDefinition = "text")
    private String clazz;
}

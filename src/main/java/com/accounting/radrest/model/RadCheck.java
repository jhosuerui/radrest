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

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "radcheck", indexes = {
    @Index(name = "radcheck_username_attr_idx", columnList = "UserName, Attribute")
})
@Getter
@Setter
public class RadCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "username", columnDefinition = "text", nullable = false)
    private String username = "";

    @NotNull
    @Column(name = "attribute", columnDefinition = "text", nullable = false)
    private String attribute = "";

    @NotNull
    @Size(max = 2)
    @Column(name = "op", length = 2, nullable = false)
    private String op = "==";

    @NotNull
    @Column(name = "value", columnDefinition = "text", nullable = false)
    private String value = "";
}

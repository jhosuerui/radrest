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
@Table(name = "radusergroup", indexes = {
    @Index(name = "radusergroup_username_idx", columnList = "UserName")
})
@Getter
@Setter
public class RadUserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "username", columnDefinition = "text", nullable = false)
    private String username = "";

    @NotNull
    @Column(name = "groupname", columnDefinition = "text", nullable = false)
    private String groupName = "";

    @NotNull
    @Column(name = "priority", nullable = false)
    private Integer priority = 0;
}

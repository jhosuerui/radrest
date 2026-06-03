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
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "gov_id")
})
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de usuario es requerido")
    @Size(max = 64, message = "El nombre de usuario no puede exceder los 64 caracteres")
    @Column(name = "username", length = 64, nullable = false)
    private String username;

    @NotBlank(message = "El nombre completo es requerido")
    @Size(max = 128, message = "El nombre completo no puede exceder los 128 caracteres")
    @Column(name = "full_name", length = 128, nullable = false)
    private String fullName;

    @NotBlank(message = "La identificación corporativa/gubernamental es requerida")
    @Size(max = 32, message = "El ID gubernamental no puede exceder los 32 caracteres")
    @Column(name = "gov_id", length = 32, nullable = false)
    private String govId;

    @NotBlank(message = "El departamento es requerido")
    @Size(max = 64, message = "El departamento no puede exceder los 64 caracteres")
    @Column(name = "department", length = 64, nullable = false)
    private String department;

    @NotNull(message = "La fecha de creación es obligatoria")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull(message = "El estado de activación es obligatorio")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}

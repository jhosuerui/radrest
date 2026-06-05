package com.accounting.radrest.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad para la gestión interna de usuarios en la plataforma Spring Boot.
 * Alineado con la sección 7.1 de la especificación de arquitectura.
 */

@Data
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 64)
    private String username;

    @Column(name = "full_name", nullable = false, length = 128)
    private String fullName;

    @Column(name = "gov_id", nullable = false, unique = true, length = 32)
    private String govId;

    @Column(name = "department", nullable = false, length = 64)
    private String department;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
package com.accounting.radrest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounting.radrest.model.Department;

/**
 * Repositorio JPA para la entidad Department.
 * Proporciona operaciones CRUD y métodos de búsqueda personalizados.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    /**
     * Busca un departamento por su nombre único.
     * @param name El nombre del departamento.
     * @return Un Optional con el departamento si se encuentra.
     */
    Optional<Department> findByName(String name);

    /**
     * Opción 1: Método derivado de Spring Data.
     * Verifica si existe un departamento con ese ID y que además esté habilitado.
     * @param id El ID del departamento.
     * @param enable El estado de habilitación (habitualmente true).
     * @return true si existe y coincide el estado, false en caso contrario.
     */
    boolean existsByNameAndEnable(String name, boolean enable);
}
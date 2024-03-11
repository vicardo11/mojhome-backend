package it.sosinski.finances.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.sosinski.finances.repository.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

}

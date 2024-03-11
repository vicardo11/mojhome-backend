package it.sosinski.finances.service;

import java.util.List;

import it.sosinski.finances.model.CategoryDto;

public interface CategoryService {

	List<CategoryDto> findAll();

}

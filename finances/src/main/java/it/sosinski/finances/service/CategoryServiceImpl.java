package it.sosinski.finances.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.sosinski.aspectlibrary.logger.LogMethodAround;
import it.sosinski.finances.model.CategoryDto;
import it.sosinski.finances.repository.CategoryRepository;
import it.sosinski.finances.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	private final CategoryMapper categoryMapper;

	@Override
	@LogMethodAround
	public List<CategoryDto> findAll() {
		return categoryRepository.findAll()
				.stream()
				.map(categoryMapper::toCategoryDto)
				.toList();
	}
}

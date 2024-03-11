package it.sosinski.finances.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import it.sosinski.finances.model.CategoryDto;
import it.sosinski.finances.model.FinanceType;
import it.sosinski.finances.repository.CategoryRepository;
import it.sosinski.finances.repository.entity.CategoryEntity;
import it.sosinski.finances.service.mapper.CategoryMapper;

@MockitoSettings
class CategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private CategoryMapper categoryMapper;

	@InjectMocks
	private CategoryServiceImpl systemUnderTest;

	@Test
	void shouldReturnCategoriesWhenCategoriesExist() {
		//Given
		List<CategoryEntity> categoryEntities = List.of(
				new CategoryEntity("1", "Groceries", FinanceType.EXPENSE, null));
		List<CategoryDto> expectedCategories = List.of(
				new CategoryDto("1", "Groceries", FinanceType.EXPENSE)
		);
		when(categoryRepository.findAll()).thenReturn(categoryEntities);
		when(categoryMapper.toCategoryDto(categoryEntities.get(0))).thenReturn(expectedCategories.get(0));

		//When
		final List<CategoryDto> actualCategories = systemUnderTest.findAll();

		//Then
		assertThat(actualCategories).isEqualTo(expectedCategories);
	}

	@Test
	void shouldReturnEmptyListWhenNoCategoriesExist() {
		//Given
		when(categoryRepository.findAll()).thenReturn(List.of());

		//When
		final List<CategoryDto> actualCategories = systemUnderTest.findAll();

		//Then
		assertThat(actualCategories).isEmpty();
	}
}
package it.sosinski.finances.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.sosinski.finances.model.CategoryDto;
import it.sosinski.finances.model.FinanceType;
import it.sosinski.finances.service.CategoryService;

@MockitoSettings
class CategoryControllerTest {

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private CategoryController systemUnderTest;

	@Test
	void shouldReturnAllCategoriesWhenCategoriesExist() {
		// Given
		List<CategoryDto> expectedCategories = Arrays.asList(
				new CategoryDto("1", "Groceries", FinanceType.EXPENSE),
				new CategoryDto("2", "Transportation", FinanceType.EXPENSE),
				new CategoryDto("3", "Insurance", FinanceType.EXPENSE)
		);
		when(categoryService.findAll()).thenReturn(expectedCategories);

		// When
		ResponseEntity<List<CategoryDto>> response = systemUnderTest.getCategories();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(expectedCategories);
	}

	@Test
	void shouldReturnEmptyListWhenNoCategoriesExist() {
		// Given
		when(categoryService.findAll()).thenReturn(List.of());

		// When
		ResponseEntity<List<CategoryDto>> response = systemUnderTest.getCategories();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEmpty();
	}
}
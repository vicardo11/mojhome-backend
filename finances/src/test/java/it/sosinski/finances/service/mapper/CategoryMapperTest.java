package it.sosinski.finances.service.mapper;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import it.sosinski.finances.model.CategoryDto;
import it.sosinski.finances.model.FinanceType;
import it.sosinski.finances.repository.entity.CategoryEntity;

class CategoryMapperTest {

    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void shouldMapToCategoryDtoWhenCategoryEntityIsNotNull() {
		//Given
        final CategoryEntity categoryEntity = new CategoryEntity("1", "Groceries", FinanceType.EXPENSE, null);

		//When
        final CategoryDto categoryDto = categoryMapper.toCategoryDto(categoryEntity);

		//Then
        assertThat(categoryDto)
            .extracting(
                CategoryDto::id,
                CategoryDto::type,
                CategoryDto::name)
            .containsExactly(
                categoryEntity.getId(),
                categoryEntity.getType(),
                categoryEntity.getName());
    }

    @Test
    void shouldReturnNullWhenCategoryEntityIsNull() {
		//When
        final CategoryDto categoryDto = categoryMapper.toCategoryDto(null);

		//Then
        assertThat(categoryDto).isNull();
    }
}
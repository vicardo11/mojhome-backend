package it.sosinski.finances.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.sosinski.finances.model.CategoryDto;
import it.sosinski.finances.repository.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	CategoryDto toCategoryDto(CategoryEntity categoryEntity);

}

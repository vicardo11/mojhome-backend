package it.sosinski.finances.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.repository.entity.FinanceEntity;

@Mapper
public interface FinanceMapper {

	FinanceMapper INSTANCE = Mappers.getMapper(FinanceMapper.class);

	@Mapping(target = "categoryName", source = "category.name")
	@Mapping(target = "categoryId", source = "category.id")
	FinanceDto toFinanceDto(FinanceEntity financeEntity);

	@Mapping(target = "userId", source = "userId")
	@Mapping(target = "category.id", source = "financeDto.categoryId")
	FinanceEntity toFinanceEntity(FinanceDto financeDto, String userId);

}

package it.sosinski.finances.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.repository.entity.FinanceEntity;

@Mapper
public interface FinanceMapper {

	FinanceMapper INSTANCE = Mappers.getMapper( FinanceMapper.class );

	 FinanceDto toFinanceDto(FinanceEntity financeEntity);

}

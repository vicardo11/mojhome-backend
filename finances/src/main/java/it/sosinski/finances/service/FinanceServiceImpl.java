package it.sosinski.finances.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.sosinski.aspectlibrary.logger.LogMethodAround;
import it.sosinski.finances.exception.model.CategoryNotFoundException;
import it.sosinski.finances.exception.model.FinanceNotFoundException;
import it.sosinski.finances.exception.model.UserMismatchException;
import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.repository.CategoryRepository;
import it.sosinski.finances.repository.FinanceRepository;
import it.sosinski.finances.repository.entity.CategoryEntity;
import it.sosinski.finances.repository.entity.FinanceEntity;
import it.sosinski.finances.service.mapper.FinanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

	private final FinanceRepository financeRepository;

	private final CategoryRepository categoryRepository;

	private final FinanceMapper financeMapper;

	@Override
	@LogMethodAround
	public List<FinanceDto> getAll(final String userId) {
		final List<FinanceEntity> entities = financeRepository.findAllByUserId(userId);
		LOG.debug("getFinancesList:: entities={}", entities);
		return entities.stream()
				.map(financeMapper::toFinanceDto)
				.toList();
	}

	@Override
	@LogMethodAround
	public FinanceDto update(final FinanceDto financeDto, final String userId) {
		final Optional<FinanceEntity> financeEntityOptional = financeRepository.findById(financeDto.id());
		final FinanceEntity financeEntity = financeEntityOptional.orElseThrow(() -> new FinanceNotFoundException(financeDto.id()));

		if (!isOwner(userId, financeEntity)) {
			LOG.error("update:: User with id={} is not allowed to update finance with id={}", userId, financeDto.id());
			throw new UserMismatchException("User is not allowed to update finance with id=" + financeDto.id());
		}

		final FinanceEntity financeEntityToSave = mapToFinanceEntity(financeDto, userId);
		final FinanceEntity savedFinanceEntity = saveToRepo(financeEntityToSave);
		return financeMapper.toFinanceDto(savedFinanceEntity);
	}

	@Override
	@LogMethodAround
	public FinanceDto create(final FinanceDto financeDto, final String name) {
		final FinanceEntity financeEntityToSave = financeMapper.toFinanceEntity(financeDto, name);
		final CategoryEntity categoryEntity = categoryRepository.findById(financeDto.categoryId())
				.orElseThrow(() -> new CategoryNotFoundException(financeDto.categoryId()));
		financeEntityToSave.setCategory(categoryEntity);
		final FinanceEntity savedFinanceEntity = saveToRepo(financeEntityToSave);
		return financeMapper.toFinanceDto(savedFinanceEntity);
	}

	@Override
	@LogMethodAround
	public List<FinanceDto> delete(final String financeId, final String userId) {
		final Optional<FinanceEntity> financeEntityOptional = financeRepository.findById(financeId);
		final FinanceEntity financeEntity = financeEntityOptional.orElseThrow(() -> new FinanceNotFoundException(financeId));
		if (!isOwner(userId, financeEntity)) {
			LOG.error("delete:: User with id={} is not allowed to delete finance with id={}", userId, financeId);
			throw new UserMismatchException("User is not allowed to delete finance with id=" + financeId);
		}
		financeRepository.deleteById(financeId);
		final List<FinanceEntity> updatedFinanceEntities = financeRepository.findAllByUserId(userId);
		return updatedFinanceEntities.stream()
				.map(financeMapper::toFinanceDto)
				.toList();
	}

	private static boolean isOwner(final String userId, final FinanceEntity financeEntity) {
		return financeEntity.getUserId().equals(userId);
	}

	private FinanceEntity saveToRepo(final FinanceEntity financeEntity) {
		return financeRepository.save(financeEntity);
	}

	private FinanceEntity mapToFinanceEntity(final FinanceDto financeDto, final String userId) {
		return financeMapper.toFinanceEntity(financeDto, userId);
	}

}

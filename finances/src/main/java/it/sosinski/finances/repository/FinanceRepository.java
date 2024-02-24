package it.sosinski.finances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sosinski.finances.repository.entity.FinanceEntity;

@Repository
public interface FinanceRepository extends JpaRepository<FinanceEntity, String> {

}

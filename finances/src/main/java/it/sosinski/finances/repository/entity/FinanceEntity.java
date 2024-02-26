package it.sosinski.finances.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import it.sosinski.finances.model.FinanceType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "finance")
public class FinanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String userId;
	private String name;
	@Enumerated(EnumType.STRING)
	private FinanceType type;
	private BigDecimal amount;
	private LocalDate date;

}

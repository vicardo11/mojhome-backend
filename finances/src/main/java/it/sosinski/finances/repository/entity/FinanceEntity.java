package it.sosinski.finances.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import it.sosinski.finances.model.FinanceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
@Entity(name = "finance")
public class FinanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable = false)
	private String userId;
	@Column(nullable = false)
	private String name;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FinanceType type;
	@Column(nullable = false)
	private BigDecimal amount;
	@Column(nullable = false)
	private LocalDate date;
	@ManyToOne
	private CategoryEntity category;

}

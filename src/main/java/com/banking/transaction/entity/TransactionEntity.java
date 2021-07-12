package com.banking.transaction.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Jesus Suarez
 */
@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionEntity extends BaseEntity {

	/** The seriaVersionUID */
	private static final long serialVersionUID = -7594199026617548012L;

	/** The reference */
	@Column(name = "REFERENCE", unique = true)
	private String reference;

	/** The accountIban */
	@Column(name = "ACCOUNT_IBAN", nullable = false)
	@NotEmpty
	private String accountIban;

	/** The date */
	@Column(name = "DATE")
	private Timestamp date;
	
	/** The amount */
	@Column(name = "AMOUNT", precision = 2)
	private Double amount;
	
	/** The fee */
	@Column(name = "FEE", precision = 2)
	private Double fee;

	/** The description */
	@Column(name = "DESCRIPTION")
	private String description;
}

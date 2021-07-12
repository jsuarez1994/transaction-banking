package com.banking.transaction.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Jesus Suarez
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionDTO implements Serializable {

	/** The seriaVersionUID */
	private static final long serialVersionUID = -7594199026617548012L;

	/** The reference */
	private String reference;

	/** The accountIban */
	private String accountIban;

	/** The date */
	private Timestamp date;

	/** The amount */
	private Double amount;

	/** The fee */
	private Double fee;

	/** The description */
	private String description;
}

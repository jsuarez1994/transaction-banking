package com.banking.transaction.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO Response
 * @author Jesus Suarez
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseStatusFeeDTO extends ResponseStatusAmountDTO implements Serializable {

	/** the serialVersionUID */
	private static final long serialVersionUID = -3287900420402296749L;
	
	/** The fee */
	private Double fee;
	

}

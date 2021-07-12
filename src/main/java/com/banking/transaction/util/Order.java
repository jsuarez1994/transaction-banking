package com.banking.transaction.util;

import org.apache.commons.lang3.StringUtils;

public enum Order {
	ASC, DESC;
	
	/**
	 * Validate if argument is valid
	 * @param order
	 * @return
	 */
	public boolean validate(String order) {
		return (!StringUtils.trimToEmpty(order).isEmpty())
				? ASC.name().equals(order.toUpperCase()) || DESC.equals(order.toUpperCase())
				: false;
	}
}

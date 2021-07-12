package com.banking.transaction.util;

import java.sql.Timestamp;
import java.util.List;

import javax.security.auth.login.AccountException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.ParseException;

import com.banking.transaction.entity.TransactionEntity;

public class Validations {
	
	/**
	 * Prepare to save
	 * @param entity
	 * @param allReferences
	 */
	public static void prepareSave(TransactionEntity entity, List<String> allReferences) throws AccountException {
		
		setReference(entity,allReferences);
		
		setAccountIban(entity);
		
		setDate(entity);
		
	}
	

	/**
	 * Generate new Reference if is Empty and not exist in system
	 * @param entity
	 * @param allReferences
	 */
	public static void setReference(TransactionEntity entity, List<String> allReferences) {
		String reference = Utils.replaceIfEmpty(entity.getReference(), StringUtils.EMPTY);
		
		if (reference.isEmpty() || allReferences.contains(reference)) {
			do {
				reference = Utils.generateReference();
			} while (allReferences.contains(reference));
		}
		entity.setReference(reference);
	}
	
	/**
	 * Validate account iban
	 * @param entity
	 */
	public static void setAccountIban(TransactionEntity entity) throws AccountException {

		String accountIban = Utils.replaceIfEmpty(entity.getAccountIban(), StringUtils.EMPTY);
		
		if(StringUtils.isEmpty(accountIban)) {
			throw new AccountException(Exceptions.ACCOUNT_IBAN_EXC);
		} else {
			if(accountIban.length() != 24) {
				throw new AccountException(Exceptions.ACCOUNT_IBAN_EXC);
			} else {
				try {
					String initIban = accountIban.substring(0,2);
					String numbersIban = accountIban.substring(2);
					
					Boolean hasLetter = Boolean.FALSE;
					
					for(int i = 0; i<numbersIban.length(); i++) {
						if (Character.isLetter(numbersIban.charAt(i))) {
							hasLetter = Boolean.TRUE;
							break;
				         }
					}
					
					if(!StringUtils.equals(initIban, "ES") || hasLetter) {
						throw new AccountException(Exceptions.ACCOUNT_IBAN_EXC);
					}
				} catch (ParseException e) {
					throw new AccountException(Exceptions.ACCOUNT_IBAN_EXC);
				}
			}
		}
	}
	
	/**
	 * Generate date transaction actually
	 * @param entity
	 */
	public static void setDate(TransactionEntity entity) {
		if(null == entity.getDate()) {
			entity.setDate(new Timestamp(System.currentTimeMillis()));
		}
	}

}

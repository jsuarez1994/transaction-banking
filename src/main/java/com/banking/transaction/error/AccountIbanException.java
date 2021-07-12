package com.banking.transaction.error;

public class AccountIbanException extends Exception {

	/** the serialVersionUID */
	private static final long serialVersionUID = -7065328692831011394L;
	
	/**
	 * Constructor vacio
	 */
	public AccountIbanException() {
		super();
	}

	/**
	 * Constructor pasandole Exception
	 * 
	 * @param e the Exception
	 */
	public AccountIbanException(Exception e) {
		super(e);
	}

	/**
	 * Constructor pasandole String msg
	 * 
	 * @param msg the msgmsg
	 */
	public AccountIbanException(String msg) {
		super(msg);
	}

}

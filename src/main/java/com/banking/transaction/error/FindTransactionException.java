package com.banking.transaction.error;

public class FindTransactionException extends Exception {

	/** the serialVersionUID */
	private static final long serialVersionUID = -7065328692831011394L;
	
	/**
	 * Constructor vacio
	 */
	public FindTransactionException() {
		super();
	}

	/**
	 * Constructor pasandole Exception
	 * 
	 * @param e the Exception
	 */
	public FindTransactionException(Exception e) {
		super(e);
	}

	/**
	 * Constructor pasandole String msg
	 * 
	 * @param msg the msgmsg
	 */
	public FindTransactionException(String msg) {
		super(msg);
	}

}

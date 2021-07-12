package com.banking.transaction.error;

public class StatusTransactionException extends Exception {

	/** the serialVersionUID */
	private static final long serialVersionUID = -7065328692831011394L;
	
	/**
	 * Constructor vacio
	 */
	public StatusTransactionException() {
		super();
	}

	/**
	 * Constructor pasandole Exception
	 * 
	 * @param e the Exception
	 */
	public StatusTransactionException(Exception e) {
		super(e);
	}

	/**
	 * Constructor pasandole String msg
	 * 
	 * @param msg the msgmsg
	 */
	public StatusTransactionException(String msg) {
		super(msg);
	}

}

package org.yangchigi.repository;

public class DuplicateEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	public DuplicateEmailException(String errorCode) {
		this.errorCode = errorCode;
	}
}

package com.armchairfun.dao;


public class UserNotFoundException extends ServerErrorException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5864461132325390597L;

	public UserNotFoundException(int errorId, String errorMessage) {
		super(errorId, errorMessage);
	}
	
}

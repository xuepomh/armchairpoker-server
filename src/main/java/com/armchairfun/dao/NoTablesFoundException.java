package com.armchairfun.dao;

public class NoTablesFoundException extends ServerErrorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5552272220507815882L;

	public NoTablesFoundException(int errorId, String errorMessage) {
		super(errorId, errorMessage);
	}
}

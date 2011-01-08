package com.armchairfun.poker.dao;

import com.armchairfun.poker.common.XMLDescribable;

public class ServerErrorException extends Exception implements XMLDescribable {


	private static final long serialVersionUID = 1852311740675230071L;
	private int errorId;
	private String errorMessage;
	
	public ServerErrorException(int errorId, String errorMessage) {
		this.errorId = errorId;
		this.errorMessage = errorMessage;
	}
	
	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<error ");
		sb.append("id=\"");
		sb.append(errorId);
		sb.append("\" message=\"");
		sb.append(errorMessage);
		sb.append("\" />");
		return sb.toString();
	}

	public int getErrorId() {
		return errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	
	
}

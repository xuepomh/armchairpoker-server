package com.armchairfun.poker.dao;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.armchairfun.poker.common.ErrorIds;

/**
 * Hibernate Implementation for a search query parameter
 * @author cjohn
 *
 */
public class HibernateSearchFilter implements SearchFilter{

	private String name;
	private String operation;
	private Object value;
	
	public HibernateSearchFilter() {
		
	}
	
	public HibernateSearchFilter(String name, String operation, String value) {
		this.name = name;
		this.operation = operation;
		this.value = value;
	}
	
	public HibernateSearchFilter(String name, String operation, Object value) {
		this.name = name;
		this.operation = operation;
		this.value = value;
	}
	
	public Criterion getRestriction() throws ServerErrorException {
		if (operation.equals("=")) {
			return Restrictions.eq(name, value);
		} else {
			throw new ServerErrorException(ErrorIds.INVALID_SEARCH_OPERATION, "Search filter operation not supported: " + operation);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	//TODO! validate the setting for operation, should only be a valid hibernate operation
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" ");
		sb.append(operation);
		sb.append(" ");
		sb.append(value);
		return sb.toString();
	}

}

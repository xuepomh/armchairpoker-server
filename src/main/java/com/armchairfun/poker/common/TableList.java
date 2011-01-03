package com.armchairfun.poker.common;

import java.util.List;

public class TableList implements XMLDescribable
{
	private List<Table> tableList;
    
    public TableList(List<Table> tableList) {
    	this.tableList = tableList;
    }
	
    public TableList(String xml) {
    	//TODO construct a table list from xml
    }
    
	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t");
		sb.append("<tableList>");
		sb.append("\n");
		for (int i = 0; i < tableList.size(); i++) { 
			Table table = tableList.get(i);
			sb.append(table.toXML());
			sb.append("\n");
		}
		sb.append("\t");
		sb.append("</tableList>");
		return sb.toString();
	}
    
}

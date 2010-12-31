package com.armchairfun.communication;

import java.util.ArrayList;
import java.util.List;

import com.armchairfun.common.XMLDescribable;

public class ServerResponse extends AbstractCommunication implements XMLDescribable{

	public List<XMLDescribable> responseItems;
	private String status;
	
	public ServerResponse() {
		this.responseItems = new ArrayList<XMLDescribable>();
	}
	
	public void addResponseItem(XMLDescribable responseItem) {
		if (responseItems != null) {
			responseItems.add(responseItem);
		}
	}
	
	public ServerResponse(List<XMLDescribable> responseItems) {
		this.responseItems = responseItems;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<response status=\"");
		sb.append(status);
		sb.append("\" >\n");
		for (XMLDescribable responseItem : responseItems) {
			sb.append(responseItem.toXML());
		}
		sb.append("\n");
		sb.append("</response>\n");
		return sb.toString();
	}
	
	
}

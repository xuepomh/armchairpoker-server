package com.armchairfun.common;

/**
 * All elements that are sent between client and server should be able to
 * describe themselves in XML
 * @author cjohn
 *
 */
public interface XMLDescribable {

	public String toXML();
}

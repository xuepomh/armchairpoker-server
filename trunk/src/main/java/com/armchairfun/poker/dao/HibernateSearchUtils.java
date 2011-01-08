package com.armchairfun.poker.dao;

import java.util.List;

import org.hibernate.Criteria;

public class HibernateSearchUtils {

	public static void applyFilters(Criteria criteria, List<SearchFilter> filters) throws ServerErrorException {
		if (filters == null || filters.size() == 0) {
			return;
		}
		
		for (SearchFilter filter: filters) {
			HibernateSearchFilter hFilter = (HibernateSearchFilter)filter;
			criteria.add(hFilter.getRestriction());
		}
	}
}

package com.armchairfun.poker.dao;

import java.util.ArrayList;
import java.util.List;

import com.armchairfun.testutils.ArmchairFunTestCase;
import com.armchairfun.poker.common.Table;

public class TestTableDao extends ArmchairFunTestCase {

	TableDao tableDao = new TableDao();

	public void testGetTables() throws Exception {
		List<Table> tables = tableDao.getTableList();
		assertTrue(tables != null);
		assertTrue(tables.size() == 2);
		assertTrue(tables.get(0).getPort() == 5557);
	}

	public void testGetTablesWithFilter() throws Exception {
		HibernateSearchFilter filter = new HibernateSearchFilter("port", "=",
				new Integer("5558"));
		List<SearchFilter> filterList = new ArrayList<SearchFilter>();
		filterList.add(filter);

		List<Table> tables = tableDao.getTableList(filterList);
		assertTrue(tables != null);
		assertTrue(tables.size() == 1);
		assertTrue(tables.get(0).getPort() == 5558);
	}

	public void testGetTablesWithFilterNoneFound() {
		HibernateSearchFilter filter = new HibernateSearchFilter("port", "=",
				new Integer(0));
		List<SearchFilter> filterList = new ArrayList<SearchFilter>();
		filterList.add(filter);

		
		try {
			tableDao.getTableList(filterList);
			fail("Should have thrown a ServerErrorException if no tables found. Filter was "
					+ filter.toString());
		} catch (Exception e) {
			assertTrue(e instanceof ServerErrorException);
		}
	}
}

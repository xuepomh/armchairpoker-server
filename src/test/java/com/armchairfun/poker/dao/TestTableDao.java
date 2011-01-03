package com.armchairfun.poker.dao;

import java.util.List;

import junit.framework.TestCase;

import com.armchairfun.poker.common.Table;
import com.armchairfun.poker.dao.TableDao;

public class TestTableDao extends TestCase {
	
	TableDao tableDao = new TableDao();


	public void testGetTables() throws Exception {
		List<Table> tables = tableDao.getTableList();
		assertTrue(tables != null);
		assertTrue(tables.size() == 2);
		assertTrue(tables.get(0).getPort() == 5557);
	}

}

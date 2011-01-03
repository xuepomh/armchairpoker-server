package com.armchairfun.poker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.armchairfun.poker.common.ErrorIds;
import com.armchairfun.poker.common.Table;
import com.armchairfun.poker.persistence.SessionFactoryUtil;

public class TableDao {

	public List<Table> getTableList() throws NoTablesFoundException {
		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();

		tx = session.beginTransaction();
		List<Table> tables = session.createCriteria(Table.class).list();
		tx.commit();
		if (tables.size() > 0) {
			return tables;
		}

		throw new NoTablesFoundException(ErrorIds.NO_TABLES_FOUND,
				"No tables found");
	}
}

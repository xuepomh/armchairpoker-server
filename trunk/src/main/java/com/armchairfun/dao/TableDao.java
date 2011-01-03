package com.armchairfun.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.armchairfun.common.ErrorIds;
import com.armchairfun.common.Table;
import com.armchairfun.persistence.SessionFactoryUtil;

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

package com.armchairfun.poker.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.armchairfun.poker.common.Table;
import com.armchairfun.poker.common.TableList;
import com.armchairfun.poker.persistence.SessionFactoryUtil;

public class LobbyDao {

	public TableList getTableList() {
		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		List<Table> tables = session.createCriteria(Table.class).list();

		TableList tableList = new TableList(tables);

		tx.commit();

		return tableList;

	}
}

package com.armchairfun.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.armchairfun.common.Table;
import com.armchairfun.common.TableList;
import com.armchairfun.persistence.SessionFactoryUtil;

public class LobbyDao {

	public TableList getTableList() {
		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance()
				.getCurrentSession();
		try {
			tx = session.beginTransaction();
			List<Table> tables = session.createCriteria(Table.class).list();

				TableList tableList = new TableList(tables);

			tx.commit();
			return tableList;
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					System.out.println("Error rolling back transaction");
				}
				throw e;
			}
			
		}
		return null;
	}
}

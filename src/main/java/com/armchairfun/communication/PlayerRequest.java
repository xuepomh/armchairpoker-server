package com.armchairfun.communication;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Document;

import com.armchairfun.common.ErrorIds;
import com.armchairfun.common.User;
import com.armchairfun.common.UserActions;
import com.armchairfun.common.utils.DOMParser;
import com.armchairfun.dao.ServerErrorException;
import com.armchairfun.dao.UserDao;
import com.armchairfun.dao.UserNotFoundException;
import com.armchairfun.persistence.SessionFactoryUtil;

/**
 * Represents a client request made to the server. Contains information on the
 * user who made the request and the values passed in. E.g. 'user1 BET 100' or
 * 'user1 JOIN_TABLE'
 * 
 * @author cjohn
 * 
 */
public class PlayerRequest extends AbstractCommunication {

	String userId;
	String action;
	String value;
	UserDao userDao = new UserDao();

	/**
	 * Parse the XML Client Request
	 */
	public PlayerRequest(String xml) {
		try {
			Document requestDoc = DOMParser.parseXml(xml);
			this.userId = DOMParser.getValueFromDocument(requestDoc, "request",
					"userId");
			this.action = DOMParser.getValueFromDocument(requestDoc, "request",
					"action");
			this.value = DOMParser.getValueFromDocument(requestDoc, "request",
					"value");
		} catch (Exception e) {
			// TODO LOG error
			throw new RuntimeException(e);
		}

	}

	public ServerResponse handleRequest() {

		ServerResponse response = new ServerResponse();

		if (action.equals(UserActions.CONNECT)) {
			Transaction tx = null;
			Session session = SessionFactoryUtil.getInstance()
					.getCurrentSession();
			try {
				tx = session.beginTransaction();
				String SQL_QUERY = "Select id from User";
				Query query = session.createQuery(SQL_QUERY);
				if (query.list() != null && query.list().size() > 0) {
					response = new ServerResponse();
					response.setStatus(ServerResponse.RESPONSE_SUCCESS);
					return response;
				}
				tx.commit();
			} catch (RuntimeException e) {
				if (tx != null && tx.isActive()) {
					try {
						tx.rollback();
					} catch (HibernateException e1) {
						System.out.println("Error rolling back transaction");
					}
				}
				response.addResponseItem(new ServerErrorException(
						ErrorIds.UNKNOWN, "runtime error: " + e.getMessage()));
			}

		} else if (action.equals(UserActions.LOAD_USER_DETAILS)) {
			User user;
			try {
				user = userDao.getUser(userId, value);
				response.setStatus(ServerResponse.RESPONSE_SUCCESS);
				response.addResponseItem(user);

			} catch (UserNotFoundException e) {
				response.setStatus(ServerResponse.RESPONSE_FAILURE);
				response.addResponseItem(new UserNotFoundException(
						ErrorIds.NO_USER_FOUND, e.getMessage()));

			}

			return response;
		} else {
			response.setStatus(ServerResponse.RESPONSE_FAILURE);
			response.addResponseItem(new ServerErrorException(
					ErrorIds.UNRECOGNIZED_ACTION, "unknown call: " + action));
		}

		return response;

	}

	public String toString() {
		return userId + " " + action + " " + value;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

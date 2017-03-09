package com.mercury.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mercury.beans.Stock;
import com.mercury.dao.StockDao;


public class StockDaoImpl implements StockDao {
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Stock stock) {
		sessionFactory.getCurrentSession().save(stock);
	}

	@Override
	public void delete(Stock stock) {
		sessionFactory.getCurrentSession().delete(stock);
	}

	@Override
	public Stock findBySid(int sid) {
		return (Stock) sessionFactory.getCurrentSession().get(Stock.class, sid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> findBySymbol(String symbol) {
		return sessionFactory.getCurrentSession().createCriteria(Stock.class)
				.add(Restrictions.eq("symbol", symbol)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> queryAll() {
		System.out.println("123");
//		query.setCacheable(true);
		return sessionFactory.getCurrentSession().createCriteria(Stock.class)
				.addOrder(Order.asc("sid")).list();
	}
}

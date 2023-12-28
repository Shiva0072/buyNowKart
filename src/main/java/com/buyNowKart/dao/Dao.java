package com.buyNowKart.dao;

import com.buyNowKart.helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface Dao {
    SessionFactory sessionFactory = FactoryProvider.getSessionFactory();

}

package session7.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BaseDao {
    SessionFactory sessionFactory = new Configuration().configure("session7/config/hibernate.cfg.xml").buildSessionFactory();



}

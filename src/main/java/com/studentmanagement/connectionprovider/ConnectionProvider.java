package com.studentmanagement.connectionprovider;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum ConnectionProvider {
    CONNECTION_PROVIDER;
    SessionFactory sessionFactory=new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
   public static SessionFactory getConnection()
   {
       return CONNECTION_PROVIDER.sessionFactory;
   }
}

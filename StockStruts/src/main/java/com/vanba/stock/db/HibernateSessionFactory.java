/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vanba.stock.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author FRAMGIA\nguyen.van.ba
 */
public class HibernateSessionFactory {

    private static SessionFactory factory;

    public static Session getSession() {
        if (factory == null) {
            Configuration config = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                    applySettings(config.getProperties());
            factory = config.buildSessionFactory(builder.build());
        }
        
        return factory.openSession();
    }
}

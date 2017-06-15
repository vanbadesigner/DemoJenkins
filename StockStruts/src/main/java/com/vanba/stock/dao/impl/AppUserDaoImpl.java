/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vanba.stock.dao.impl;

import com.vanba.stock.db.HibernateSessionFactory;
import com.vanba.stock.model.AppUser;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.vanba.stock.dao.AppUserDao;

/**
 *
 * @author FRAMGIA\nguyen.van.ba
 */
public class AppUserDaoImpl implements AppUserDao {

    public static AppUserDao personDao;

    private AppUserDaoImpl() {

    }

    public static synchronized AppUserDao getInstance() {
        if (personDao == null) {
            personDao = new AppUserDaoImpl();
        }

        return personDao;

    }
    
    public boolean checkLogin(AppUser person){
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = null;
        boolean isValid = false;
        try {
            transaction = session.beginTransaction();
            Query hQuery = session.createQuery("from AppUser A Where A.name = :userName and A.pass = :userPass");
            hQuery.setParameter("userName", person.getName());
            hQuery.setParameter("userPass", person.getPass());
            isValid = hQuery.list().size() == 1;
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return isValid;
    }

    public Integer addPerson(AppUser person) {
        Session session = HibernateSessionFactory.getSession();
        Integer personId = 0;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            personId = (Integer) session.save(person);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return personId;
    }

    public boolean updatePerson(AppUser person) {
        Session session = HibernateSessionFactory.getSession();
        boolean isSuccess = false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            AppUser personUpdate = (AppUser) session.get(AppUser.class, person.getId());
            personUpdate.setName(person.getName());
            personUpdate.setEmail(person.getEmail());
            personUpdate.setPass(person.getPass());
            session.update(personUpdate);
            transaction.commit();
            isSuccess = true;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return isSuccess;
    }

    public List<AppUser> getAllPersons() {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = null;
        List<AppUser> listPersons = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            listPersons = session.createQuery("from AppUser").list();

            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return listPersons;
    }

    public AppUser getPersonById(int id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = null;
        AppUser person = null;
        try {
            transaction = session.beginTransaction();
            person = (AppUser) session.get(AppUser.class, id);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return person;
    }

}

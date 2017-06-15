/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vanba.stock.dao.impl;

import com.vanba.stock.db.HibernateSessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.vanba.stock.dao.ProductDao;
import com.vanba.stock.model.Product;

/**
 *
 * @author FRAMGIA\nguyen.van.ba
 */
public class ProductDaoImpl implements ProductDao {

    public static ProductDao productDao;

    private ProductDaoImpl() {

    }

    public static synchronized ProductDao getInstance() {
        if (productDao == null) {
            productDao = new ProductDaoImpl();
        }

        return productDao;

    }
    

    public Integer addProduct(Product product) {
        Session session = HibernateSessionFactory.getSession();
        Integer id = 0;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(product);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return id;
    }

    public boolean updateProduct(Product product) {
        Session session = HibernateSessionFactory.getSession();
        boolean isSuccess = false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Product productUpdate = (Product) session.get(Product.class, product.getId());
            productUpdate.setName(product.getName());
            productUpdate.setAvailableStock(product.getAvailableStock());
            productUpdate.setUnit(product.getUnit());
            session.update(productUpdate);
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
    
    public boolean deleteProduct(int id) {
        Session session = HibernateSessionFactory.getSession();
        boolean isSuccess = false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Product productUpdate = (Product) session.get(Product.class, id);
            if(productUpdate == null){
                return isSuccess;
            }
            session.delete(productUpdate);
            transaction.commit();
            Product productDeleted = (Product) session.get(Product.class, id);
            isSuccess = productDeleted == null;
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

    public List<Product> getAllProducts() {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = null;
        List<Product> listProducts = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            listProducts = session.createQuery("from Product").list();

            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return listProducts;
    }

    public Product getProductById(int id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = null;
        Product product = null;
        try {
            transaction = session.beginTransaction();
            product = (Product) session.get(Product.class, id);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return product;
    }

}

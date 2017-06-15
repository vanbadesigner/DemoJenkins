/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vanba.stock.dao;

import com.vanba.stock.model.Product;
import java.util.List;

/**
 *
 * @author FRAMGIA\nguyen.van.ba
 */
public interface ProductDao {

    public Integer addProduct(Product product);

    public boolean updateProduct(Product product);

    public List<Product> getAllProducts();

    public Product getProductById(int id);
    
    public boolean deleteProduct(int id);
}

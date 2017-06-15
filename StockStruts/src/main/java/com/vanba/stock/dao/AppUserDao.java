/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vanba.stock.dao;

import com.vanba.stock.model.AppUser;
import java.util.List;

/**
 *
 * @author FRAMGIA\nguyen.van.ba
 */
public interface AppUserDao {

    public Integer addPerson(AppUser person);

    public boolean updatePerson(AppUser person);

    public List<AppUser> getAllPersons();

    public AppUser getPersonById(int id);

    public boolean checkLogin(AppUser person);
}

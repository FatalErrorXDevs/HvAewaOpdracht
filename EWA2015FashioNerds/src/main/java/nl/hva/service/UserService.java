/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.service;

import java.util.List;
import nl.hva.dao.AccountDAO;
import nl.hva.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tom Prins
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private AccountDAO accountDAO;

    public nl.hva.model.User findAccount(String username) {
        return accountDAO.findAccount(username);
    }

    public nl.hva.model.User findAccount(int id) {
        return accountDAO.findAccount(id);
    }

    public List<nl.hva.model.User> searchAll(String search) {
        return accountDAO.searchAll(search);
    }

    public List<nl.hva.model.User> getAllAccounts() {
        return accountDAO.findAll();
    }

    public List<nl.hva.model.User> findAllByUsername(String username) {
        return accountDAO.findAllByUsername(username);
    }

    public void saveOrUpdate(User account) throws Exception {
        accountDAO.saveOrUpdate(account);
    }

    public void update(nl.hva.model.User account) throws Exception {
        accountDAO.update(account);
    }

    public void addRole(UserRole role) {

        accountDAO.addRole(role);
    }

    public void delete(User account) {
        accountDAO.delete(account);
    }

    public void saveOrUpdate(nl.hva.model.User account) {

        accountDAO.saveOrUpdate(account);
    }

    public UserRole getUserRoleById(int role) {
        return accountDAO.getUserRoleById(role);
    }

    public List<UserRole> getAllRoles() {
        return accountDAO.getAllRoles();
    }
    
    public long count()
    {
        return accountDAO.count();
    }

}

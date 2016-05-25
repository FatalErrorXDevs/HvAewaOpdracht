package nl.hva.dao;

import java.util.List;
import nl.hva.model.User;
import nl.hva.model.UserRole;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * This class handles adding, searching, editing and deleting for an Account
 * Object.
 *
 * @version 1.0
 * @since 09-10-2015
 * @author Bert Kooij
 */
@Repository
public class AccountDAO extends AbstractDAO{

    private String hql;
    private Query query;

    public User findAccount(String username) {
        hql = "from nl.hva.model.User where username= :username";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("username", username);
        List l = query.list();

        return (l != null && l.size() > 0) ? (User) l.get(0) : null;
    }

    /**
     * Find all accounts.
     *
     * @return List with accounts.
     */
    public List<User> findAll() {
        Session session = getCurrentSession();

        String hql = "from User";
        Query query = session.createQuery(hql);
        return query.list();
    }

    /**
     * Search all accounts.
     *
     * @param search
     * @return List with accounts.
     */
    public List<User> searchAll(String search) {
        Session session = getCurrentSession();

        String hql = "FROM User WHERE LOWER(GebruikersNaam) LIKE LOWER(:Search)";
        Query query = session.createQuery(hql);
        query.setParameter("Search", "%" + search + "%");
        return query.list();
    }
    
    public void addRole(UserRole role){
    
        Session session = getCurrentSession();
        session.saveOrUpdate(role);
    }

    /**
     * Find single Account by ID
     *
     * @param accountID
     * @return Account
     */
    public User findAccount(int accountID) {
        Session session = getCurrentSession();

        String hql = "from User where Id = :Id";
        Query query = session.createQuery(hql);
        query.setParameter("Id", accountID);
        return (User) query.list().get(0);
    }

    /**
     * Find single Account by Username
     *
     * @param username
     * @return Account
     */
    public List<User> findAllByUsername(String username) {
        Session session = getCurrentSession();
        String hql = "from User where GebruikersNaam = :Username";
        Query query = session.createQuery(hql);
        query.setParameter("Username", username);
        return query.list();
    }

    /**
     * Deletes a single Account.
     *
     * @param account instance of object that needs to be deleted.
     */
    public void delete(User account) {
        Session session = getCurrentSession();
        session.delete(account);
    }

    /**
     * Saves an account.
     *
     * @param account instance of Account that needs to be saved.
     * @throws java.lang.Exception
     */
    public void saveOrUpdate(User account) {
        Session session = getCurrentSession();
        session.saveOrUpdate(account);
    }
    
    public void update(User account) {
        Session session = getCurrentSession();
        session.update(account);
    }
    
    public void saveOrUpdate(org.springframework.security.core.userdetails.User account) {
                Session session = getCurrentSession();
        session.saveOrUpdate(account);
    }

    public void delete(org.springframework.security.core.userdetails.User account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public UserRole getUserRoleById(int role)
    {
        Session session = getCurrentSession();

        String hql = "from UserRole where userRoleId = :Role";
        Query query = session.createQuery(hql);
        query.setParameter("Role", role);
        return (UserRole) query.list().get(0);
    }
    
    public List<UserRole> getAllRoles()
    {
        Session session = getCurrentSession();
        String hql = "from UserRole";
        Query query = session.createQuery(hql);
        return query.list();
    }
    
    public long count() {
        Session session = getCurrentSession();
        String hql = "SELECT COUNT(*) FROM User";
        Query query = session.createQuery(hql);
        long count = (long) query.list().get(0);
        return count;
    }

}

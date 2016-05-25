package nl.hva.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import nl.hva.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tom Prins
 */
@Repository
public class UserDaoImpl extends AbstractDAO implements UserDao {
 

    @SuppressWarnings("unchecked")
    @Override
    public User findByUserName(String username) {

        List<User> users = new ArrayList<User>();

        users = getCurrentSession()
                .createQuery("from User where username=?")
                .setParameter(0, username)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

}

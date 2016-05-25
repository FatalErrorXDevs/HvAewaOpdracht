package nl.hva.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bert
 */
@Repository
public abstract class AbstractDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        if (sessionFactory.getCurrentSession().isOpen()) {
            return sessionFactory.getCurrentSession();
        }
        return sessionFactory.openSession();
    }
    
}

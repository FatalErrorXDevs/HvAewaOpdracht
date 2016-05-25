package nl.hva.dao;

import java.util.List;
import nl.hva.model.Announcement;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @since 03-01-2016
 * @author Floris van Lent
 */
@Repository
public class AnnouncementDAO extends AbstractDAO{

    public List<Announcement> getAll() {
        String hql = "from Announcement";
        
        List<Announcement> announcements =
            (List<Announcement>) getCurrentSession().createQuery(hql).list();
        
        for(Announcement a :announcements)
        {
            Hibernate.initialize(a.getCreatedBy());
            Hibernate.initialize(a.getLastModifiedBy());
        }
        
        return announcements;
    }
    
    public Announcement getById(int id) {
        String hql = "from Announcement WHERE id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setInteger("id", id);
        
        return (Announcement) query.list().get(0);
    }
    
    public void save(Announcement announcement) {
        getCurrentSession().save(announcement);
    }
    
    public void update(Announcement announcement) {
        getCurrentSession().update(announcement);
    }
    
    public void delete(Announcement announcement) {
        getCurrentSession().delete(announcement);
    }
}

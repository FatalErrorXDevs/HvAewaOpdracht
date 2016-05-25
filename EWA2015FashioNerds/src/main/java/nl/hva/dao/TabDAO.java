package nl.hva.dao;

import java.util.List;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.Tab;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * This class handles adding, searching, editing and deleting for an Account Object.
 *
 * @version 1.0
 * @since 09-10-2015
 * @author Yassine
 */
@Repository
public class TabDAO  extends AbstractDAO{
    
    public List<Tab> getAll() {
        String hql = "from Tab";
        List<Tab> tabs = getCurrentSession().createQuery(hql).list();
        for (Tab tab : tabs) {
            Hibernate.initialize(tab.getCategories());
            for(Category cat : tab.getCategories())
            {
                Hibernate.initialize(cat.getData());
            }
        }

        return tabs;
    } 
    
    public Tab getByName(String name) {
        String hql = "from Tab WHERE name = :name";
        Query query = getCurrentSession().createQuery(hql);
        query.setString("name", name);

        return (Tab) query.list().get(0);
    }

    public Tab getById(int id) {
        String hql = "from Tab WHERE id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setInteger("id", id);

        return (Tab) query.list().get(0);
    }

    public void save(Tab tab) {
        getCurrentSession().save(tab);
    }

    public void update(Tab tab) {
        getCurrentSession().update(tab);
    }

    public void delete(Tab tab) {
        getCurrentSession().delete(tab);
    }
}

package nl.hva.dao;

import java.util.List;
import nl.hva.model.fabric.data.Data;
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
public class DataDAO extends AbstractDAO{

    public List<Data> getAll()
    {
        String hql = "from Data";
        
        return getCurrentSession().createQuery(hql).list();
    }
    
    public Data getByName(String name)
    {
        String hql = "from Data WHERE name = :name";
        Query query = getCurrentSession().createQuery(hql);
        query.setString("name", name);
        
        return (Data) query.list().get(0);
    }
    
    public Data getById(int id)
    {
        String hql = "from Data WHERE id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setInteger("id", id);
        Data data = (Data) query.list().get(0);
        Hibernate.initialize(data.getCategory());
        return data;
    }
    
    public void saveOrUpdate(Data data)
    {
        getCurrentSession().saveOrUpdate(data);
    }
}

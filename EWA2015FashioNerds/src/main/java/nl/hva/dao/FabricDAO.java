package nl.hva.dao;

import java.util.List;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.filter.FilterModel;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

/**
 * This class handles adding, searching, editing and deleting for an Account Object.
 *
 * @version 1.0
 * @since 09-10-2015
 * @author Yassine, Ternel, Bert
 */
@Repository
public class FabricDAO extends AbstractDAO {

    public List viewFabrics(String FabricName) {

        List<Fabric> results;

        String hql = "from Fabric where LOWER(FabricName) LIKE LOWER(:FabricName) "
                + "OR LOWER(color) LIKE LOWER(:FabricName) "
                + "OR LOWER(weight) LIKE LOWER(:FabricName) "
                + "OR LOWER(width) LIKE LOWER(:FabricName) "
                + "OR LOWER(state) LIKE LOWER(:FabricName)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("FabricName", "%" + FabricName + "%");
        results = query.list();

        return results;

    }

    public int getMaxWeight(int defaultValue) {
        String hql = "SELECT MAX(weight) FROM Fabric";
        Query query = getCurrentSession().createQuery(hql);

        List<Integer> results = query.list();
        if (results == null) {
            return defaultValue;
        }

        return (results.get(0) == null) ? defaultValue : results.get(0);
    }

    public int getMax(String column, int defaultValue) {

        Criteria criteria = getCurrentSession().createCriteria(Fabric.class)
                .setProjection(Projections.max(column));
        Integer max = (Integer) criteria.uniqueResult(); 
        return max;
    }

    public List<Fabric> searchMultipleFabrics(FilterModel filterModel) {
//        Criteria cr = getCurrentSession().createCriteria(Fabric.class, "hql");
//        
//
//        filterModel.useCriteria(cr);
//        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        List<Fabric> results = cr.list();
//        return (results != null && !results.isEmpty()) ? results : null;
        
        List<Fabric> list = this.getAllFabrics();
        filterModel.filterList(list);
        return list;
    }

    /**
     * Saves an fabric.
     *
     * @param fabric
     * @throws java.lang.Exception
     */
    public void save(Fabric fabric) throws Exception {
        fabric.beforeFirstSave();

        getCurrentSession().save(fabric);
    }

    public void update(Fabric fabric) throws Exception {
        fabric.beforeUpdate();
        getCurrentSession().update(fabric); 
    }

    /**
     *
     * @return results
     */
    public List<Fabric> getAllFabrics() {
        Session session = getCurrentSession();  
        Query query = session.createQuery("from Fabric");
        return query.list();
    }

    public Fabric getFabricById(int id) {
        String hql = "from Fabric f where f.id= :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        Fabric fabric;
        //(Fabric) query.list().get(0);
        if (query.list().size() > 0) {
            fabric = (Fabric) query.list().get(0); 
            Hibernate.initialize(fabric.getWorkedOn()); 
            return fabric;
        } else {
            return null;
        }
    }

    public long count() {
        Session session = getCurrentSession();
        String hql = "SELECT COUNT(*) FROM Fabric";
        Query query = session.createQuery(hql);
        long count = (long) query.list().get(0);
        return count;
    }
    
    public List<Fabric> searchFavoriteFabricByName(int accountID, String FabricName) {

        Session session = getCurrentSession();

        String hql = "Select o from Users r join r.favoriteFabrics o where r.Id = :accountID "
                + "AND LOWER(FabricName) LIKE LOWER(:FabricName) "
                + "OR LOWER(color) LIKE LOWER(:FabricName) "
                + "OR LOWER(weight) LIKE LOWER(:FabricName) "
                + "OR LOWER(width) LIKE LOWER(:FabricName) "
                + "OR LOWER(state) LIKE LOWER(:FabricName) ";
        Query query = session.createQuery(hql);
        query.setParameter("FabricName", "%" + FabricName + "%");
        query.setParameter("accountID", accountID);
        List<Fabric> results = (List<Fabric>) query.list();
        for (int i = 0; i < results.size(); i++) {
            results.get(i).getFabricName();
        }
        return results;

    }

}
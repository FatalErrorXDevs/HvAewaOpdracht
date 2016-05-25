package nl.hva.dao;

import java.util.List;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.CategorySingle;
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
public class CategoryDAO extends AbstractDAO{
    
    public List<Category> getAll() {
        String hql = "from Category";
        List<Category> categories = getCurrentSession().createQuery(hql).list();
        for (Category category : categories) {
            Hibernate.initialize(category.getData());
        }
        return categories;
    }

    public List<Category> getFilters() {
        String hql = "from Category where filter = true";

        List<Category> filters = getCurrentSession().createQuery(hql).list();
        for (Category category : filters) {
            Hibernate.initialize(category.getData());
        }
        return filters;
    }

    public List<CategorySingle> getAllCategoriesSingle() {
        String hql = "from CategorySingle";

        return getCurrentSession().createQuery(hql).list();
    }

    public Category getByName(String name) {
        String hql = "from Category WHERE name = :name";
        Query query = getCurrentSession().createQuery(hql);
        query.setString("name", name);

        return (Category) query.list().get(0);
    }

    public Category getById(int id) {
        String hql = "from Category WHERE id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setInteger("id", id);
        Category cat = (Category) query.list().get(0);
        Hibernate.initialize(cat.getData());
        return cat;
    }

    public void save(Category category) {
        getCurrentSession().save(category);
    }

    public void update(Category category) {
        getCurrentSession().update(category);
    }

    public void delete(Category category) {
        getCurrentSession().delete(category);
    }

    public void getFilterFromCategory(Category category) {
        Hibernate.initialize(category.getData());
    }
}

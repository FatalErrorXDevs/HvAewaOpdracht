package nl.hva.dao;

import java.util.List;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.Project;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * This class handles adding, searching, editing and deleting for an Account
 * Object.
 *
 * @version 1.0
 * @since 09-10-2015
 * @author Yassine
 */
@Repository
public class ProjectDAO extends AbstractDAO{

    private String hql;
    private Query query;

    /**
     *
     * @param FabricName
     * @return
     * @todo LOCATION OF THIS METHOD
     */
    public List getProjects() {

        Session session = getCurrentSession();

        hql = "from Fabric";
        query = session.createQuery(hql);
        List<Fabric> results = query.list();

        return results;

    }

    public List<Project> searchProjectByName(int projectId, String projectName) {

        Session session = getCurrentSession();

        hql = "Select o from Users r join r.ProjectAccounts o where r.Id = :AccountID "
                + "AND LOWER(ProjectName) LIKE LOWER(:ProjectName) ";
        query = session.createQuery(hql);
        query.setParameter("ProjectName", "%" + projectName + "%");
        query.setParameter("AccountID", projectId);
        List<Project> results = (List<Project>) query.list();
        for (int i = 0; i < results.size(); i++) {
            results.get(i).getProjectName();
        }
        return results;

    }

    public Project AddProject(Project project) {

        Session session = getCurrentSession();
        session.save(project);
        return project;
    }

    public Project getProjectById(int projectID) {
        Session session = getCurrentSession();
        hql = "from Project where ProjectID = :Id";
        query = session.createQuery(hql);
        query.setParameter("Id", projectID);
        Project project = (Project) query.list().get(0);
        return project;
    }

    public void saveOrUpdate(Project project) {
        Session session = getCurrentSession();
        session.saveOrUpdate(project);
    }

    public void delete(Project project) {
        getCurrentSession().delete(project);
    }
    
    public long count() {
        Session session = getCurrentSession();
        String hql = "SELECT COUNT(*) FROM Project";
        Query query = session.createQuery(hql);
        long count = (long) query.list().get(0);
        return count;
    }
}

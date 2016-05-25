package nl.hva.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import nl.hva.dao.ProjectDAO;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yassine
 */
@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    /**
     *
     * @return
     */
    public List<Fabric> getProjects() {
        return projectDAO.getProjects();
    }

    public void addProject(Project project) {
        projectDAO.AddProject(project);
    }

    public Project getProjectById(int projectID) {
        return projectDAO.getProjectById(projectID);
    }

    public List<Project> searchProjectByName(int projectId, String projectName) {
        return projectDAO.searchProjectByName(projectId, projectName);
    }

    public void saveOrUpdate(Project project) {

        projectDAO.saveOrUpdate(project);
    }

    public void delete(Project project) {
        projectDAO.delete(project);
    }
    public long count()
    {
        return projectDAO.count();
    }
}

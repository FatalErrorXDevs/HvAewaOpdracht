package nl.hva.model;

import nl.hva.model.fabric.Fabric;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ternel
 */
@Entity(name = "Project")
public class Project implements Serializable {

    @Id
    @GeneratedValue
    private int ProjectID;

    private String ProjectName;
    private String ProjectDescription;
    private int createdById;

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    @ManyToMany(mappedBy = "projects_fabric")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Fabric> fabrics;

    @ManyToMany(mappedBy = "projects")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Account> account;

    public Project() {
    }

    public Project(int ProjectID, String ProjectName, String ProjectDescription, int createdById) {
        this.ProjectID = ProjectID;
        this.ProjectName = ProjectName;
        this.ProjectDescription = ProjectDescription;
        this.createdById = createdById;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getProjectDescription() {
        return ProjectDescription;
    }

    public void setProjectDescription(String ProjectDescription) {
        this.ProjectDescription = ProjectDescription;
    }

    public List<Account> getFavoriteFabrics() {
        return account;
    }

    public void setFavoriteFabrics(List<Account> account) {
        this.account = account;
    }

    public List<Fabric> getFabrics() {
        return fabrics;
    }

    public void setFabrics(List<Fabric> fabrics) {
        this.fabrics = fabrics;
    }

    @Override
    public String toString() {
        return "Project{" + "ProjectID=" + ProjectID + ", ProjectName=" + ProjectName + ", ProjectDescription=" + ProjectDescription + ", fabrics=" + fabrics + ", account=" + account + '}';
    }

}

package nl.hva.model.fabric;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import nl.hva.model.AbstractEntity;
import nl.hva.model.Project;
import nl.hva.model.User;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.data.Data;
import nl.hva.model.fabric.data.TempData;
import nl.hva.model.fabricdata.completeness.CountComplete;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The {@code Fabric} class represents a real world fabric. A Fabric contains
 * fabricdata.
 *
 * @version 2.0
 * @since 1.0
 * @author Yassine el Abdellaoui, Bert Kooij, Ternel Barzey
 */
@Entity(name = "Fabric")
public class Fabric extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private int Id;

    @CountComplete(priority = 5)
    private String FabricName;
    
    @ManyToMany(mappedBy="favoriteFabrics")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<User> userFavorites = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name="User_Worked_On_Fabric",
          joinColumns=@JoinColumn(name="fabric"),
          inverseJoinColumns=@JoinColumn(name="user"))
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<User> workedOn = new HashSet<>();
    
    
    @ManyToMany()
    @JoinTable(name = "Project_Fabrics", joinColumns = {
        @JoinColumn(name = "FabricID")},
            inverseJoinColumns = {
                @JoinColumn(name = "ProjectID")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Project> projects_fabric;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Data> data = new ArrayList<>();

    @Transient
    private List<TempData> tempData = new ArrayList<>();

    public Fabric() {
    }

    @Override
    public Integer getId() {
        return Id;
    }

    /**
     * @return the FabricName
     */
    public String getFabricName() {
        return FabricName;
    }

    /**
     * @param FabricName the FabricName to set
     */
    public void setFabricName(String FabricName) {
        this.FabricName = FabricName;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public boolean hasCategory(Category category) {
        int i = 0;
        for (Data data : data) {
            if (data.getCategory().equals(category)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public int getCompleteness(List<Category> categories) {
        this.resetCompleteness();
        for (Category category : categories) {
            if (data != null && hasCategory(category)) {
                this.addCompleted(category.getWeight());
            }
            this.addTotal(category.getWeight());
        }
        return getPercentageComplete(this);
    }

    /**
     * @author bbbYassine & Ternel
     * @param account
     * @return
     */
    public boolean hasFavorite(User user) {
        //list van alle favoriteFabrics
        List<Fabric> favoriteFabricsList = user.getFavoriteFabrics();

        //loop door hele lijst van favorites
        for (Fabric fabric : favoriteFabricsList) {
            if (fabric.getId() == getId()) {
                return true;
            }
        }
        return false;
    }
//     public boolean hasProject(Project project) {
//        //list van alle favoriteFabrics
//        List<Fabric> ProjectList = project.getFabrics();
//
//        //loop door hele lijst van favorites
//        for (Fabric fabric : favoriteFabricsList) {
//            if (fabric.getId() == getId()) {
//                return true;
//            }
//        }
//        return false;
//    }

    public List<Data> getFabricDataByCategory(Category category) {
        return getFabricDataByCategory(category.getId());
    }
    
    public List<Data> getFabricDataByCategory(int category) {

        List<Data> results = new ArrayList<>();
        for (Data data : data) {
            if (data.getCategory().getId() == category) {
                results.add(data);
            }
        }
        return results;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public void addData(Data data) {
        if(this.data == null)
        {
             this.data = new ArrayList<>();
        }
        this.data.add(data);
    }

    public void addTempData(TempData data) {
        this.tempData.add(data);
    }

    public List<TempData> getTempData() {
        return tempData;
    }

    public void setTempData(List<TempData> tempData) {
        this.tempData = tempData;
    }

    public Data getDataForCategoryId(int id) {
        for (Data dataItem : data) {
            if (dataItem.getCategory().getId() == id) {
                return dataItem;
            }
        }
        return null;
    }

    public int getTempDataIndexForCategoryId(int id) {
        int i = 0;
        for (TempData dataItem : tempData) {
            if (dataItem.getCategoryId() == id) {
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public void addWorkedOn(User user)
    {
        workedOn.add(user);
    }

    public void setWorkedOn(Set<User> workedOn) {
        this.workedOn = workedOn;
    } 
    
    public Set<User> getWorkedOn() {
        return workedOn;
    }
    
    @Override
    public boolean isNew() {
        return (Id == 0);
    } 

    public List<Project> getProject() {
        return projects_fabric;
    }

    public void setProject(List<Project> projects) {
        this.projects_fabric = projects;
    }
}
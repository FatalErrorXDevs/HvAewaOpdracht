package nl.hva.model.fabric;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import nl.hva.model.AbstractEntity;
import nl.hva.model.fabric.Category;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Bert
 */
@Entity
public class Tab extends AbstractEntity {
    
    @Id
    @GeneratedValue
    private int id;
    
    private String name;
    
    private boolean generalTab;
    
    @OneToMany(mappedBy = "tab")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Category> categories = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    } 

    public boolean isGeneralTab() {
        return generalTab;
    }

    public void setGeneralTab(boolean generalTab) {
        this.generalTab = generalTab;
    }

    @Override
    public boolean isNew() {
        return (id==0);
    } 
    
}

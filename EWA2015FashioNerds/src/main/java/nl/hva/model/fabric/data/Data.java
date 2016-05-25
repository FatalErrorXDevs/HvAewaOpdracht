package nl.hva.model.fabric.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.Fabric;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Bert
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Data  implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    /**
     * Fabrics who contains this FabricData.
     */
    @ManyToMany(mappedBy = "data")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Fabric> fabrics;
    
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private Category category; 
    
    
    @Column(columnDefinition = "TEXT")
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Fabric> getFabrics() {
        return fabrics;
    }

    public void setFabrics(List<Fabric> fabrics) {
        this.fabrics = fabrics;
    }

    @Override
    public String toString() {
        return "Data{" + "id=" + id + ", category=" + category + ", value=" + value + '}';
    }
    
    public String toExport() {
        return getValue();
    }
    
    public boolean isEmpty()
    {
        return value == null || value.isEmpty();
    }
}

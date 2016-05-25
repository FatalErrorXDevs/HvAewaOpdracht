package nl.hva.model.fabric;

import nl.hva.model.fabric.data.Data;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import nl.hva.input.Input;
import nl.hva.input.InputLoader;
import nl.hva.input.exception.InputNotImplementedException;
import nl.hva.input.InputType;
import nl.hva.model.AbstractEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Bert
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Category extends AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    
    private String unit;

    /**
     * Description of Category.
     */
    private String description;

    private int weight = 5;

    @OneToMany(mappedBy = "category")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Data> data = new ArrayList<>();

    @ManyToOne
    @LazyCollection(LazyCollectionOption.TRUE)
    private Tab tab;

    private int dataType;

    private boolean filter;

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Data> getData() {
        return data;
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public InputType getDataType() {
        return InputType.parse(dataType);
    }

    public void setDataType(InputType dataType) {
        this.dataType = dataType.getId();
    }

    public Input getInput() throws InputNotImplementedException {
        return InputLoader.getInput(getDataType());
    }

    public void add(Data data) {
        this.data.add(data);
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean isNew() {
        return (id == 0);
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final Category other = (Category) obj;
        return this.id == other.id;
    }

}

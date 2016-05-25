package nl.hva.filters;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;

/**
 * The abstract {@code Filter} class represents a Filter. A Filter is used for showing a Filter in a
 * layout.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.0
 */
public abstract class Filter implements Serializable {

    /**
     * Name of filter. (Required)
     */
    private String name;

    /**
     * Description of filter.
     */
    private String description;

    /**
     * Unit of filter. (for example: meters, liters or g/m<sup>2</sup>)
     */
    private String unit;

    /**
     * Value of filter.
     */
    private String value;

    /**
     * Id of filter, must be unique and is generated based on the name of the filter by default. You
     * must set a different ID on duplicate filternames.
     */
    private String id;

    /**
     * Filedname in class (Used for filters).
     */
    private String fieldName;

    /**
     * Referencing object (Used for filters).
     */
    private String view;
    
    private int categoryId;

    /**
     * Constructs an empty Filter.
     */
    public Filter() {
    }

    /**
     * Constructs a filter.
     *
     * @param name
     * @param description
     * @param fieldName
     * @param view
     */
    public Filter(String name, String description, String fieldName, String view) {
        this.name = name;
        this.description = description;
        this.view = view;
        this.fieldName = fieldName;
        this.id = filterAlpha(name);
    }

    /**
     *
     * @param name
     * @param fieldName
     * @param view
     */
    public Filter(String name, String fieldName, String view) {
        this.name = name;
        this.view = view;
        this.fieldName = fieldName;
        this.id = filterAlpha(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return (description == null || description.isEmpty()) ? null : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = filterAlpha(id);
    }

    public String getValue() {
        return "" + value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public abstract void filterList(List list);

    /**
     * Only return a-zA-Z0-9 characters of a String.
     *
     * @param string
     * @return
     */
    private String filterAlpha(String string) {
        return string.replaceAll("[^A-Za-z0-9]+", "").toLowerCase();
    }

    /**
     * Apply createria for this filter. This must be overridden in most cases!
     *
     * @param criteria Criteria instance.
     * @return Criteria instance with nieuw criteria added.
     * @todo Fix criteria's
     */
    public Criteria useCriteria(Criteria criteria) {
//        criteria.add(Restrictions.ilike(fieldName, "%" + value + "%"));
        return criteria;
    }

    /**
     * Get tag for Filter. This will be used to display which filters are applied for the User.
     *
     * @return String representation of selected values.
     */
    public String getTag() {
        return getName() + ": " + getValue();
    }

    @Override
    public String toString() {
        return "Filter{" + "name=" + name + ", description=" + description + ", unit=" + unit + ", value=" + value + ", id=" + id + ", fieldName=" + fieldName + ", view=" + view + '}';
    }
    
}

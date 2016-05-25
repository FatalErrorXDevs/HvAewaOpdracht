package nl.hva.filters;

import java.util.List;
import org.hibernate.Criteria;

/**
 * The {@code RadioFilter} class represents a filter with radio buttons for filtering one-to-many or
 * many-to-many relations. A RadioFilter extends the {@code FilterWithItems} class. It contains
 * multiple items and can have a single selectedValue.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.0
 */
public class RadioFilter extends FilterWithItems {

    /**
     * Location of Filter jsp file.
     */
    private static final String VIEW_LOCATION = "../filters/radio.jsp";
    
    /**
     * Default value name. 
     * @todo implement translation
     */
    private static final String DEFAULT_VALUE = "Geen voorkeur";
    
    /**
     * Default id.
     */
    private static final int DEFAULT_ID = -1;

    /**
     * Constructs a new RadioFilter.
     *
     * @param name
     * @param description
     * @param fieldName
     */
    public RadioFilter(String name, String description, String fieldName) {
        super(name, description, fieldName, VIEW_LOCATION); 
        this.addItem(DEFAULT_ID, DEFAULT_VALUE);
        setValue(DEFAULT_VALUE);
    }

    public RadioFilter() {
        this.setView(VIEW_LOCATION);
    }
    
    

    /**
     * Apply createria for this filter.
     *
     * @param criteria Criteria instance.
     * @return Criteria instance with nieuw criteria added.
     * @todo Fix criteria's
     */
    @Override
    public Criteria useCriteria(Criteria criteria) {
        if (getValue().equals(DEFAULT_VALUE)) {
            return criteria;
        }
//        return super.useCriteria(criteria); 
        return criteria;
    }

    @Override
    public void filterList(List list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

package nl.hva.filters;

import nl.hva.filters.interfaces.FilterAndOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.fabric.data.Data;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

/**
 * The {@code CheckFilter} class represents a filter with checkboxes for filtering many-to-many
 * relations. A CheckFilter extends the {@code FilterWithItems} class. It contains multiple items
 * and can have multiple selectedValues.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.0
 */
public class CheckFilter extends FilterWithItems implements FilterAndOption {

    /**
     * Location of Filter jsp file.
     */
    private static final String VIEW_LOCATION = "../filters/check.jsp";

    /**
     * Selected values. (Id's of {@code FilterItem})
     */
    private List<Integer> selectedValues = new ArrayList<>();

    /**
     * RelationName used for filters.
     */
    private String relationName;

    /**
     * Show and option
     */
    boolean andOption = false;

    /**
     * All selected options must be available when criteria is applied.
     */
    private boolean useAsAnd = false;

    /**
     * Constructs a new CheckFilter.
     *
     * @param name
     * @param description
     * @param fieldName
     * @param relationName
     */
    public CheckFilter(String name, String description, String fieldName, String relationName) {
        super(name, description, fieldName, VIEW_LOCATION);
        this.relationName = relationName;
    }

    public CheckFilter() {
        this.setView(VIEW_LOCATION);
    }

    public List<Integer> getSelectedValues() {
        return selectedValues;
    }

    public void setSelectedValues(List<Integer> selectedValues) {
        this.selectedValues = selectedValues;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
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
        if (selectedValues != null && selectedValues.size() > 0) {
//            criteria.createCriteria("data", "dataCheckAlias" + getId());
//            criteria.createCriteria("dataCheckAlias" + getId()+".category", "data_category" + getId());
//            criteria.createAlias("dataAlias", "data" + getId());
//            criteria.createCriteria("data.category", "categoryAlias" + getId());
//            criteria.add(Restrictions.eq("categoryAlias" + getId() + ".id", getCategoryId()));
//            criteria.createCriteria("categoryAlias" + getId() + ".data", "data" + getId());
//            if (isUseAsAnd()) {
//                Map<String, Integer> hm = new HashMap<String, Integer>();
//                for (int sel : selectedValues) {
//                    hm.put("data" + getId() + ".id", sel);
//                }
//                criteria.add(Restrictions.allEq(hm));
//            } else {
//                criteria.add(Restrictions.in("data" + getId() + ".id", selectedValues));
//            }

            DetachedCriteria subCriteria = DetachedCriteria
                    .forClass(Data.class);
            subCriteria.add(Restrictions.in("id", selectedValues));
            criteria.add(Subqueries.propertyEq("data", subCriteria));
        }
        return criteria;
    }

    /**
     * Get tag for Filter. This will be used to display which filters are applied for the User.
     *
     * @return String representation of selected values.
     */
    @Override
    public String getTag() {
        if (selectedValues == null || selectedValues.isEmpty()) {
            return "";
        }
        String ret = getName() + ": ";

        for (Integer item : selectedValues) {
            ret += item + ",";
        }
        return ret;
    }

    @Override
    public boolean isUseAsAnd() {
        return useAsAnd;
    }

    @Override
    public void setUseAsAnd(boolean useAsAnd) {
        this.useAsAnd = useAsAnd;
    }

    @Override
    public boolean isAndOption() {
        return andOption;
    }

    @Override
    public void setAndOption(boolean andOption) {
        this.andOption = andOption;
    }

    @Override
    public String toString() {
        return "CheckFilter{" + "selectedValues=" + selectedValues + ", relationName=" + relationName + '}';
    }

    public boolean accept(Object obj) {
        if (obj instanceof Fabric) {
            Fabric fabric = (Fabric) obj; 
            for (Data data : fabric.getFabricDataByCategory(getCategoryId())) {
                for (Integer item : selectedValues) {
                    if (Objects.equals(data.getId(), item)) { 
                        return true;
                    }
                } 
            }  
        }
        return false;
    }
    
    public boolean acceptAll(Object obj) {
        if (obj instanceof Fabric) {
            Fabric fabric = (Fabric) obj;
            
            int numSelected = selectedValues.size();
            int numApplied = 0;
            
            for (Data data : fabric.getFabricDataByCategory(getCategoryId())) {
                for (Integer item : selectedValues) {
                    if (Objects.equals(data.getId(), item)) { 
                        numApplied++;
                    } 
                }  
            }  
            if(numSelected == numApplied)
            {
                return true;
            }
        } 
        return false;
    }

    @Override
    public void filterList(List list) {
        if (selectedValues != null && selectedValues.size() > 0) {
            for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
                Object obj = iterator.next(); 
                
                if ((!acceptAll(obj) && useAsAnd) || (!accept(obj) && !useAsAnd)) {
                    iterator.remove();
                }
            }
        }
    }
}

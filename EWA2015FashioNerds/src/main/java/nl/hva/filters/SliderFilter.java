package nl.hva.filters;

import java.util.Iterator;
import java.util.List;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.fabric.data.Data;
import nl.hva.model.fabric.data.DataNumber;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * The {@code SliderFilter} class represents a filter with slider input for filtering a single
 * column. A SliderFilter extends the {@code Filter} class. It adds min values, max values and
 * stepsize for the slider.
 *
 * @author Bert Kooij
 * @version 2.0
 * @since 2.0
 */
public class SliderFilter extends Filter {

    /**
     * Location of Filter jsp file.
     */
    private static final String VIEW_LOCATION = "../filters/slider.jsp";

    /**
     * Default stepsize.
     */
    private static final int DEFAULT_STEP_SIZE = 10;

    /**
     * Default min value.
     */
    private static final int DEFAULT_MIN_VALUE = 0;

    /**
     * Default max value.
     */
    private static final int DEFAULT_MAX_VALUE = 300;

    /**
     * Stepsize of filter.
     */
    private int stepSize = DEFAULT_STEP_SIZE;

    /**
     * Min selectable value
     */
    private int minSelectableValue = DEFAULT_MIN_VALUE;

    /**
     * Max selectable value
     */
    private int maxSelectableValue = DEFAULT_MAX_VALUE;
    /**
     * min value of slider.
     */
    private int minValue = DEFAULT_MIN_VALUE;

    /**
     * max valeu of slider.
     */
    private int maxValue = DEFAULT_MAX_VALUE;

    /**
     * Constructs a new SliderFilter.
     *
     * @param name
     * @param description
     * @param fieldName
     */
    public SliderFilter(String name, String description, String fieldName) {
        super(name, description, fieldName, VIEW_LOCATION);
    }

    public SliderFilter() {
        this.setView(VIEW_LOCATION);
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinSelectableValue() {
        return minSelectableValue;
    }

    public void setMinSelectableValue(int minSelectableValue) {
        this.minSelectableValue = minSelectableValue;
    }

    public int getMaxSelectableValue() {
        return maxSelectableValue;
    }

    public void setMaxSelectableValue(int maxSelectableValue) {
        this.maxSelectableValue = maxSelectableValue;
    }

    /**
     * Apply createria for this filter.
     *
     * @param criteria Criteria instance.
     * @return Criteria instance with nieuw criteria added.
     */
    @Override
    public Criteria useCriteria(Criteria criteria) {
//            criteria.createAlias("data", "dataSliderAlias" + getId());
//            criteria.createAlias("dataCheckAlias" + getId()+".category", "data_category" + getId());
        criteria.add(Restrictions.eq("categoryAlias.id", getCategoryId()));
        criteria.add(Restrictions.between("dataAlias.valueNum", minValue, maxValue));
        return criteria;
    }

    /**
     * Get tag for Filter. This will be used to display which filters are applied for the User.
     *
     * @return String representation of selected values.
     */
    @Override
    public String getTag() {
        return getName() + ": " + getMinValue() + "-" + getMaxValue();
    }

    public boolean accept(Object obj) {
        if (obj instanceof Fabric) {
            Fabric fabric = (Fabric) obj;
            Data data = (Data) fabric.getDataForCategoryId(getCategoryId());
            if (data instanceof DataNumber) {
                DataNumber dataNumber = (DataNumber) data;

                if (dataNumber.getValueNum() >= getMinValue() && dataNumber.getValueNum() <= getMaxValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void filterList(List list) {
        for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
            Object obj = iterator.next();

            if (!accept(obj)) {
                iterator.remove();
            }
        }
    }

}

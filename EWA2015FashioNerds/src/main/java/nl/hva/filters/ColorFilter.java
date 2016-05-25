package nl.hva.filters;

import java.util.Iterator;
import java.util.List;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.fabric.data.Data;
import nl.hva.model.fabric.data.DataColor;
import org.hibernate.Criteria;

/**
 * The {@code SliderFilter} class represents a filter with slider input for filtering a single
 * column. A SliderFilter extends the {@code Filter} class. It adds min values, max values and
 * stepsize for the slider.
 *
 * @author Bert Kooij
 * @version 2.0
 * @since 2.0
 */
public class ColorFilter extends Filter {

    /**
     * Location of Filter jsp file.
     */
    private static final String VIEW_LOCATION = "../filters/color.jsp";

    /**
     * Constructs a new SliderFilter.
     *
     * @param name
     * @param description
     * @param fieldName
     */
    public ColorFilter(String name, String description, String fieldName) {
        super(name, description, fieldName, VIEW_LOCATION);
    }

    public ColorFilter() {
        this.setView(VIEW_LOCATION);
    }

    /**
     * Apply createria for this filter.
     *
     * @param criteria Criteria instance.
     * @return Criteria instance with nieuw criteria added.
     */
    @Override
    public Criteria useCriteria(Criteria criteria) {
        return criteria;
    }

    /**
     * Get tag for Filter. This will be used to display which filters are applied for the User.
     *
     * @return String representation of selected values.
     */
    @Override
    public String getTag() {
        if (getValue() == null || getValue().isEmpty()) {
            return "";
        }
        return getName() + ": " + getValue();
    }

    public boolean accept(Object obj) {
        if (obj instanceof Fabric) {
            Fabric fabric = (Fabric) obj;
            Data data = (Data) fabric.getDataForCategoryId(getCategoryId());
            if (data instanceof DataColor) {
                DataColor dataColor = (DataColor) data;
                int[] rgb = DataColor.parseRGB(getValue());
                int r = rgb[0];
                int g = rgb[1];
                int b = rgb[2];

                if (dataColor.getR() >= minus(r) && dataColor.getR() <= maxus(r)
                        && dataColor.getG() >= minus(g) && dataColor.getG() <= maxus(g)
                        && dataColor.getB() >= minus(b) && dataColor.getB() <= maxus(b)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int minus(int rgb) {
        rgb -= 40;
        return (rgb < 0) ? 0 : rgb;
    }

    private int maxus(int rgb) {
        rgb += 40;
        return (rgb > 255) ? 255 : rgb;
    }

    @Override
    public void filterList(List list) {
        if (getValue() != null && !getValue().isEmpty() && getValue().length() > 5) {

            for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
                Object obj = iterator.next();

                if (!accept(obj)) {
                    iterator.remove();
                }
            }
        }
    }

}

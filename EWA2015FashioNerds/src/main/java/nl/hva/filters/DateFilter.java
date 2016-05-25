package nl.hva.filters;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The {@code DateFilter} class represents a filter for dates. A DateFilter extends the
 * {@code Filter} class. It contains one date input field.
 *
 * @author Bert Kooij
 * @version 2.0
 * @since 2.0
 * @todo Implement this filter with minDate and maxDate.
 */
public class DateFilter extends Filter implements Serializable {

    /**
     * Location of Filter jsp file.
     */
    private static final String VIEW_LOCATION = "../filters/date.jsp";

    /**
     * Min selectedDate.
     */
    private Date minDate;
    
    /**
     * Max selectedDate.
     */
    private Date maxDate;

    public DateFilter(String name, String description, String fieldName) {
        super(name, description, fieldName, VIEW_LOCATION);
    }

    public DateFilter() {
        this.setView(VIEW_LOCATION);
    }
    
    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    } 

    @Override
    public String toString() {
        return "DateFilter{" + "minDate=" + minDate + ", maxDate=" + maxDate + '}';
    }

    @Override
    public void filterList(List list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

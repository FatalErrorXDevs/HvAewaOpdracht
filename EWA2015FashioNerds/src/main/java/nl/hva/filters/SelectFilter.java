package nl.hva.filters;

import java.util.List;

/**
 * The {@code SelectFilter} class represents a filter with a selectmenu for filtering one-to-many or
 * many-to-many relations. A SelectFilter extends the {@code FilterWithItems} class. It contains
 * multiple items and can have a single selectedValue.
 *
 * @author Bert Kooij
 * @version 2.0
 * @since 2.0
 */
public class SelectFilter extends FilterWithItems {

    /**
     * Location of Filter jsp file.
     */
    private static final String VIEW_LOCATION = "../filters/select.jsp";

    /**
     * Constructs a new SelectFilter.
     *
     * @param name
     * @param description
     * @param fieldName
     */
    public SelectFilter(String name, String description, String fieldName) {
        super(name, description, fieldName, VIEW_LOCATION);
    }

    public SelectFilter() {
        this.setView(VIEW_LOCATION);
    }

    @Override
    public void filterList(List list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

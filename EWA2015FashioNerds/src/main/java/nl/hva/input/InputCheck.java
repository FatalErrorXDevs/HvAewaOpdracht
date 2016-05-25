package nl.hva.input;

import nl.hva.filters.CheckFilter;
import nl.hva.filters.Filter;

/**
 *
 * @author Bert
 */
public class InputCheck extends Input {

    @Override
    public String getInputFile() {
        return "/WEB-INF/jsp/input/check.jsp";
    }

    @Override
    public Filter getFilter() {
        Filter filter = new CheckFilter();
        return filter;
    }
    
}
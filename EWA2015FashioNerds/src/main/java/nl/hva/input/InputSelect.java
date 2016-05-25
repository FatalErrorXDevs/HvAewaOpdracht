package nl.hva.input;

import nl.hva.filters.CheckFilter;
import nl.hva.filters.Filter;
import nl.hva.filters.SelectFilter;

/**
 *
 * @author Bert
 */
public class InputSelect extends Input {

    @Override
    public String getInputFile() {
        return "/WEB-INF/jsp/input/select.jsp";
    }

    @Override
    public Filter getFilter() {
        return new CheckFilter();
    }
    
}

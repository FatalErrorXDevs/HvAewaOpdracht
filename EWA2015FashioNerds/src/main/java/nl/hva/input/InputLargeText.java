package nl.hva.input;

import nl.hva.filters.Filter;
import nl.hva.filters.SelectFilter;

/**
 *
 * @author Bert
 */
public class InputLargeText extends Input {

    @Override
    public String getInputFile() {
        return "/WEB-INF/jsp/input/largeText.jsp";
    }

    @Override
    public Filter getFilter() {
        return new SelectFilter();
    }
}
package nl.hva.input;

import nl.hva.filters.Filter;
import nl.hva.filters.SelectFilter;
import nl.hva.filters.SliderFilter;
import static nl.hva.input.InputColor.REGEX;
import nl.hva.model.fabric.data.DataSingle;
import org.springframework.validation.Errors;

/**
 *
 * @author Bert
 */
public class InputNumber extends Input {

    public static final String REGEX = "\\\\d+";

    @Override
    public String getInputFile() {
        return "/WEB-INF/jsp/input/number.jsp";
    }

    /**
     * @todo Improved error messages.
     * @param errors
     * @param path
     * @param dataSingle 
     */
    @Override
    public void checkErrors(Errors errors, String path, DataSingle dataSingle) {
        if (dataSingle.getValue() != null && !dataSingle.getValue().isEmpty()) {
            if (!dataSingle.getValue().matches(REGEX)) {
//                errors.rejectValue(path, "Error");
            }
        }
    }

    @Override
    public Filter getFilter() {
        SliderFilter filter = new SliderFilter();
        filter.setMaxSelectableValue(500);
        filter.setMinSelectableValue(0);
        return filter;
    }

}

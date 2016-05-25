package nl.hva.input;

import nl.hva.filters.ColorFilter;
import nl.hva.filters.Filter;
import nl.hva.filters.SelectFilter;
import static nl.hva.input.InputNumber.REGEX;
import nl.hva.model.fabric.data.DataSingle;
import org.springframework.validation.Errors;

/**
 *
 * @author Bert
 */
public class InputColor extends Input {

    public static final String REGEX = "/rgb\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*\\)$/";
    
    @Override
    public String getInputFile() {
        return "/WEB-INF/jsp/input/color.jsp";
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
        return new ColorFilter();
    }
    
}
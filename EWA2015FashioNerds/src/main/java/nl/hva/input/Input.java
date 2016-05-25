package nl.hva.input;

import nl.hva.filters.Filter;
import nl.hva.model.fabric.data.DataSingle;
import org.springframework.validation.Errors;

/**
 *
 * @author Bert
 */
public abstract class Input {
    
    
    public abstract String getInputFile();
    
    public void checkErrors(Errors errors, String path, DataSingle dataSingle)
    {
    }
    
    public abstract Filter getFilter();
}

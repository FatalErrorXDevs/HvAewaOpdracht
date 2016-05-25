package nl.hva.validator;

import nl.hva.input.Input;
import nl.hva.input.InputLoader;
import nl.hva.input.exception.InputNotImplementedException;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.data.DataSingle;
import nl.hva.model.fabric.data.TempData;
import nl.hva.service.fabric.CategoryService;
import nl.hva.service.fabric.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This is the validator for the model Account.
 *
 * @version 1.0
 * @since 09-10-2015
 * @author Bert Kooij
 */
@Service
public class FabricValidator implements Validator {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private transient DataService dataService;

    @Override
    public boolean supports(Class<?> type) {
        return Fabric.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Fabric fabric = (Fabric) o;
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "FabricName", "required.GebruikersNaam");
//        errors.rejectValue("FabricName", "Error");
        int i = 0;
        for (TempData data : fabric.getTempData()) {
            DataSingle dataSingle;
            Category category;
            if (data.getDataId() != 0) {
                dataSingle = (DataSingle) dataService.getById(data.getDataId());
                category = dataSingle.getCategory();
            } else {
                category = categoryService.getById(data.getCategoryId());
                dataSingle = (DataSingle) InputLoader.getDataType(category.getDataType());
                dataSingle.setCategory(category);
            }
            dataSingle.setValue(data.getValue());

            String errorPath = "tempData["+i+"]";
            try {
                Input input = InputLoader.getInput(category.getDataType());
                input.checkErrors(errors, errorPath, dataSingle);
            } catch (InputNotImplementedException ex) {
                errors.rejectValue(errorPath, "Error");
            }
 
            dataService.saveOrUpdate(dataSingle);
            fabric.addData(dataSingle);
            i++;
        }
    }

}

package nl.hva.input;

import nl.hva.input.exception.InputNotImplementedException;
import nl.hva.model.fabric.data.Data;
import nl.hva.model.fabric.data.DataColor;
import nl.hva.model.fabric.data.DataMulti;
import nl.hva.model.fabric.data.DataNumber;
import nl.hva.model.fabric.data.DataSingle;

/**
 *
 * @author Bert
 */
public class InputLoader {

    public static Input getInput(InputType inputType) throws InputNotImplementedException {
        switch (inputType) {
            case RADIO:
                return new InputRadio();
            case CHECK:
                return new InputCheck();
            case SELECT:
                return new InputSelect();
            case TEXT:
                return new InputText();
            case LARGE_TEXT:
                return new InputLargeText();
            case NUMBER:
                return new InputNumber();
            case COLOR:
                return new InputColor();
            default:
                throw new InputNotImplementedException("InputType is not (yet) implemented. Did you add another case in " + InputLoader.class + "?");
        }
    }
    
    public static Data getDataType(InputType inputType)
    {
         switch (inputType) {
            case COLOR:
                return new DataColor();
            case NUMBER:
                return new DataNumber();
            case RADIO:
                return new DataMulti();
            case CHECK:
                return new DataMulti();
            case SELECT:
                return new DataMulti();
            default:
                return new DataSingle();
        }
    }

}

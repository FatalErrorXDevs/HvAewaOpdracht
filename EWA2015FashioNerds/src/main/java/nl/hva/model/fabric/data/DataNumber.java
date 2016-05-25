package nl.hva.model.fabric.data;

import javax.persistence.Entity;

/**
 *
 * @author Bert
 */
@Entity
public class DataNumber extends DataSingle {

    private int valueNum = 0; 

    @Override
    public void setValue(String value) {
        super.setValue(value);
        if(value!=null || value.isEmpty())
        {
            try
            {
                valueNum = Integer.parseInt(value);
            } 
            catch(NumberFormatException ex)
            { 
            }
        } 
    }

    public int getValueNum() {
        return valueNum;
    }

    public void setValueNum(int valueNum) {
        this.valueNum = valueNum;
    } 
    
}

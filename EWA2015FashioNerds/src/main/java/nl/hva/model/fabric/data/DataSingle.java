package nl.hva.model.fabric.data;

import javax.persistence.Entity;

/**
 *
 * @author Bert
 */
@Entity
public class DataSingle extends Data { 

    public int getDataId() {
       return super.getId();
    } 
    
}

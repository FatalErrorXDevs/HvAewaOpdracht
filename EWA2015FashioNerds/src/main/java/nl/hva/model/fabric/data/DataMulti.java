package nl.hva.model.fabric.data;

import javax.persistence.Entity;

/**
 *
 * @author Bert
 */
@Entity
public class DataMulti extends Data {

    private boolean reviewed = false;

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }
    
}

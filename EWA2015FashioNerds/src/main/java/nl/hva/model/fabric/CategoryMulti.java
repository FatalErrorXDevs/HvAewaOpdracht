package nl.hva.model.fabric;

import javax.persistence.Entity;

/**
 *
 * @author Bert
 */
@Entity
public class CategoryMulti extends Category {

    /**
     * Whether users must be able to add multiple values for this category.
     */
    private boolean multiSelect = false;
    
    private boolean userInput = false;
    
    private boolean reviewRequired = false;

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public boolean isUserInput() {
        return userInput;
    }

    public void setUserInput(boolean userInput) {
        this.userInput = userInput;
    }

    public boolean isReviewRequired() {
        return reviewRequired;
    }

    public void setReviewRequired(boolean reviewRequired) {
        this.reviewRequired = reviewRequired;
    }
    
}

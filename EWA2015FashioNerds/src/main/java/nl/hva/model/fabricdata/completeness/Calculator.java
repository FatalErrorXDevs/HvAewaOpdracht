package nl.hva.model.fabricdata.completeness;

/**
 * The {@code Calculator} class is a interface for calculations of completeness.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.1
 */
public interface Calculator {

    /**
     * Get the percentage of complition for a entity.
     * 
     * @param object
     * @return percentage between 1-100
     */
    public int getPercentageComplete(Object object);

}

package nl.hva.model.fabricdata.completeness;

/**
 * The {@code Completeness} class is a interface for providing a custom method. This method will be
 * used for the calculation.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.1
 */
public interface Completeness {

    /**
     * Whether the instance must be counted as empty or not.
     *
     * @return true when the instance is empty.
     */
    public boolean isEmpty();

}

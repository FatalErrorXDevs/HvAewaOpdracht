package nl.hva.model.fabricdata.completeness;

import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * The {@code CalculatorImpl} class is a implementation of the calculation interface for counting
 * completeness of a entity. This class provides all the methods for calculation the completion of a
 * entity as well as a base for calculating custom fields.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.1
 */
@MappedSuperclass
public abstract class CalculatorImpl implements Calculator {

    /**
     * CheckClass for primitive datatypes.
     */
    @Transient
    CheckClass[] checks = {
        new CheckClass(int.class, 0),
        new CheckClass(double.class, 0.0),
        new CheckClass(boolean.class, false),
        new CheckClass(float.class, 0.0),
        new CheckClass(char.class, 0),
        new CheckClass(byte.class, 0),
        new CheckClass(long.class, 0.0),
        new CheckClass(String.class, null),
        new CheckClass(String.class, "")
    };

    /**
     * Total possible of completion points.
     */
    @Transient
    private int total = 1;

    /**
     * Ammount of completion points for this instance.
     */
    @Transient
    private int completed = 1;

    /**
     * Get the percentage of complition for a entity.
     * 
     * @param object
     * @return percentage between 1-100
     * @todo reset calculation when this method is not overriden.
     */
    @Override
    public int getPercentageComplete(Object object) {
        calculateFields(object);
        return calculatePercentage();
    }

    /**
     * Calculate a percentage.
     * 
     * @return percentage between 1-100 
     */
    protected int calculatePercentage() {
        return (int) ((completed * 100.0f) / total);
    }

    /**
     * Calculate all fields with annotation.
     * 
     * @param object 
     */
    private void calculateFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            CountComplete annos = field.getAnnotation(CountComplete.class);
            if (annos != null) {
                int prio = annos.priority();
                total += prio;
                try {
                    if (!isEmpty(field, object)) {
                        completed += prio;
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    total -= prio;
                }

            }
        }
    }

    /**
     * Checks whether a field is empty.
     * @param f field
     * @param object instance for field
     * @return true when field is empty.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    private boolean isEmpty(Field f, Object object)
            throws IllegalArgumentException, IllegalAccessException {

        /**
         * If field is not initialized the field is empty.
         */
        if (f.get(object) == null) {
            return true;
        }

        /**
         * If the field type implements {@code Completeness} than the method isEmpty can be used.
         */
        if (Completeness.class.isAssignableFrom(f.getType())) {
            return ((Completeness) f.get(object)).isEmpty();
        }

        /**
         * If the field type implements {@code List} than the method isEmpty can be used.
         */
        if (java.util.List.class.isAssignableFrom(f.getType())) {
            List l = ((List) f.get(object));
            return l.isEmpty();
        }

        /**
         * Check the premitive classes.
         */
        for (CheckClass check : checks) {
            if (check.isEmpty(f, object)) {
                return true;
            }
        }

        /**
         * The field is not empty.
         */
        return false;
    }

    /**
     * Get total of possible completion points.
     * @return 
     */
    protected int getTotal() {
        return total;
    }

    /**
     * add to total of possible completion points.
     */
    protected void addTotal(int total) {
        this.total += total;
    }

    /**
     * Get total of completion points for this instance.
     * @return 
     */
    protected int getCompleted() {
        return completed;
    }

    /**
     * add to total of completion points for this instance.
     * @param completed
     */
    protected void addCompleted(int completed) {
        this.completed += completed;
    }

    /**
     * Reset counting.
     */
    protected void resetCompleteness() {
        completed = 1;
        total = 1;
    }

}

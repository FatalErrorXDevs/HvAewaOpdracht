package nl.hva.model.fabricdata.completeness;

import java.lang.reflect.Field;

/**
 * The {@code CheckClass} class is used for checking primaraly primitive datatypes.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.1
 */
public class CheckClass {

    private final Class classType;

    private final Object compare;

    public CheckClass(Class classType, Object compare) {
        this.classType = classType;
        this.compare = compare;
    }

    public Class getClassType() {
        return classType;
    }

    public Object getCompare() {
        return compare;
    }

    public boolean isEmpty(Field f, Object object) throws IllegalArgumentException, IllegalAccessException {
        return getClassType() == f.getType()
                && f.get(object).equals(getCompare());
    }

}

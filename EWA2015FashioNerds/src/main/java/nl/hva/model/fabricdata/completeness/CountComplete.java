package nl.hva.model.fabricdata.completeness;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The {@code CountComplete} class is a annotation interface for counting completeness. Atributes
 * that must be counted for completion must annotate this interface. Optionally a priority can be
 * provided, the priority will be used for calculating the completeness of a entity.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.1
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CountComplete {

    int priority() default 1;

}

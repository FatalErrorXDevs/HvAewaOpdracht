package nl.hva.model;

import java.util.Calendar;

/**
 *
 * @author Bert
 * @version 1.0
 * @since 08-10-2015
 */
public class Page {

    private final int year = Calendar.getInstance().get(Calendar.YEAR);

    private String leftInclude = "menus/Default.jsp";
 

    public Page() {
    }

    public Page(String leftInclude) {
        this.leftInclude = leftInclude;
    }

    public String getLeftInclude() {
        return leftInclude;
    }

    public int getYear() {
        return year;
    }

    public void setLeftInclude(String leftInclude) {
        this.leftInclude = leftInclude;
    }

}

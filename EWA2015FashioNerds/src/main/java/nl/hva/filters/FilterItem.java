package nl.hva.filters;

import java.io.Serializable;

/**
 * The {@code FilterItem} class represents a item for a {@code Filter}. A FilterItem is used in
 * {@code FilterWithItems} and contains an id and a name.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.1
 */
public class FilterItem implements Serializable {

    /**
     * Id of FilterItem.
     */
    private int id;

    /**
     * Name of FilterItem.
     */
    private String name;

    /**
     * Constructs new FilterItem.
     *
     * @param id
     * @param name
     */
    public FilterItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FilterItem{" + "id=" + id + ", name=" + name + '}';
    }

}

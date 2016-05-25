package nl.hva.filters;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code FilterWithItems} class represents a filter with multiple items. This abstract class
 * can be extended for filters with checkboxes, radiobuttons, select menus etc.
 *
 * @author Bert Kooij
 * @version 2.0
 * @since 2.0
 */
public abstract class FilterWithItems extends Filter {

    /**
     * Items for filter.
     */
    private List<FilterItem> items = new ArrayList();

    /**
     * Constructs a new FilterWithItems.
     *
     * @param name
     * @param description
     * @param fieldName
     * @param view
     */
    public FilterWithItems(String name, String description, String fieldName, String view) {
        super(name, description, fieldName, view);
    }

    public FilterWithItems() {
    }

    /**
     * Add a item to the Filter.
     *
     * @param id
     * @param value
     */
    public void addItem(int id, String value) {
        addItem(new FilterItem(id, value));
    }

    /**
     * Add a item to the Filter.
     *
     * @param item
     */
    public void addItem(FilterItem item) {
        items.add(item);
    }

    public List<FilterItem> getItems() {
        return items;
    }

    /**
     * Remove all items in the filter.
     */
    public void removeItems() {
        items = new ArrayList();
    }

    @Override
    public String toString() {
        return "FilterWithItems{" + "items=" + items + '}';
    }

}

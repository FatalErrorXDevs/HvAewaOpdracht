package nl.hva.filters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;

/**
 * The {@code FilterGroup} class represents a group of filters. A FilterGroup contains multiple
 * {@code Filter}s.
 *
 * @author Bert Kooij
 * @version 2.0
 * @since 2.0
 */
public class FilterGroup implements Serializable {

    /**
     * Name of the filterGroup.
     */
    private String name;

    /**
     * Filters inside filterGroup.
     */
    private List<Filter> filters = new ArrayList();

    /**
     * Constructs new FilterGroup.
     *
     * @param name
     */
    public FilterGroup(String name) {
        this.name = name;
    }

    /**
     * Add a new Filter to the FilterGroup.
     *
     * @param filter instance of a subclass of Filter.
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    /**
     * Removes all Filters from this filterGroup.
     */
    public void clear() {
        filters = new ArrayList();
    }

    /**
     * Applies the Filters on a Criteria.
     *
     * @param criteria instance of Criteria.
     * @return instance of criteria with new criteria`s added.
     */
    public Criteria useCriteria(Criteria criteria) {
        for (Filter filter : filters) {
            filter.useCriteria(criteria);
        }
        return criteria;
    }

    public void filterList(List list)
    {
        for (Filter filter : filters) {
            filter.filterList(list);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    @Override
    public String toString() {
        return "FilterGroup{" + "name=" + name + ", filters=" + filters + '}';
    }

}

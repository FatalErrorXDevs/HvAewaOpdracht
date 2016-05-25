package nl.hva.model.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import nl.hva.filters.Filter;
import nl.hva.filters.FilterGroup;
import org.hibernate.Criteria;

/**
 * The {@code FabricFilter} class represents a {@code FilterModel} for a Fabrics.
 *
 * @author Bert Kooij
 * @version 2.0
 * @since 2.0
 */
public abstract class FilterModel implements Serializable {

    /**
     * Multiple {@code FilterGroup}s
     */
    private final List<FilterGroup> filterGroups = new ArrayList<>();

    /**
     * User has triggered some filters.
     */
    private boolean userFilter = false;

    /**
     * Constructs an empty FilterMode;.
     */
    public FilterModel() {
    }

    protected abstract void createFilters();

    /**
     * Clear model of all FilterGroups.
     */
    private void clear() {
        filterGroups.clear();
    }

    public abstract FilterModel build();

    /**
     * Adds a FilterGroup.
     *
     * @param g Instance of FilterGroup.
     */
    public void addFilterGroup(FilterGroup g) {
        filterGroups.add(g);
    }

    public List getFilterGroups() {
        return filterGroups;
    }

    /**
     * Applies the Filters on a Criteria.
     *
     * @param criteria instance of Criteria.
     * @return instance of criteria with new criteria`s added.
     */
    public Criteria useCriteria(Criteria criteria) {
        for (FilterGroup group : filterGroups) {
            group.useCriteria(criteria);
        }
        return criteria;
    }

    public void filterList(List list) {
        for (FilterGroup group : filterGroups) {
            group.filterList(list);
        }
    }

    public boolean isUserFilter() {
        return userFilter;
    }

    public void setUserFilter(boolean userFilter) {
        this.userFilter = userFilter;
    }

    /**
     * Get all filters inside Model.
     *
     * @return
     */
    public List<Filter> getFilters() {
        List<Filter> result = new ArrayList<>();
        for (FilterGroup group : filterGroups) {
            result.addAll(group.getFilters());
        }
        return result;
    }

    /**
     * Rounds a int up to a 100 value.
     *
     * @param n
     * @return
     */
    protected int roundUp(int n) {
        return ((n + 99) / 100) * 100;
    }
}

package nl.hva.model.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import nl.hva.filters.FilterGroup;
import nl.hva.filters.SliderFilter;
import nl.hva.filters.SortItem;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.Tab;
import nl.hva.service.fabric.CategoryService;
import nl.hva.service.fabric.TabService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 * The {@code FabricFilter} class represents a {@code FilterModel} for a Fabrics.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.0
 * @todo translations.
 */
public class FabricFilter extends FilterModel implements Serializable {

    private ArrayList<SortItem> sortItems = new ArrayList<>();

    private ArrayList<Category> categories = new ArrayList<>();

    private String selectedSort = "2";

    private transient CategoryService categoryService;

    private transient TabService tabService;

    private int maxWeight = 500;
    private int maxWidth = 500;
    private int maxUsableWidth = 500;
    /**
     * @todo translation
     */
    private final FilterGroup generalGroup = new FilterGroup("Algemeen");
    private final FilterGroup widthGroup = new FilterGroup("Width");
    private final FilterGroup dataGroup = new FilterGroup("Data");

    /**
     * Constructs a new FabricFilter.
     */
    public FabricFilter() {
    }

    private void buildSortList() {
        sortItems.add(new SortItem(0, "Newest first", Order.desc("Id"), new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Fabric f1 = (Fabric) o1;
                Fabric f2 = (Fabric) o2;

                return f2.getId().compareTo(f1.getId());
            }

        }));
        sortItems.add(new SortItem(1, "Oldest first", Order.asc("Id"), new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Fabric f1 = (Fabric) o1;
                Fabric f2 = (Fabric) o2;

                return f1.getId().compareTo(f2.getId());
            }

        }));
        sortItems.add(new SortItem(2, "A-Z", Order.asc("FabricName"), new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Fabric f1 = (Fabric) o1;
                Fabric f2 = (Fabric) o2;

                return f1.getFabricName().compareTo(f2.getFabricName());
            }

        }));
        sortItems.add(new SortItem(3, "Z-A", Order.desc("FabricName"), new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Fabric f1 = (Fabric) o1;
                Fabric f2 = (Fabric) o2;

                return f2.getFabricName().compareTo(f1.getFabricName());
            }

        }));
        sortItems.add(new SortItem(4, "Most completed", Order.desc("Most completed"), new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Fabric f1 = (Fabric) o1;
                Fabric f2 = (Fabric) o2;
                List<Category> cats = categoryService.getAll();
                Integer i1 = f1.getCompleteness(cats);
                Integer i2 = f2.getCompleteness(cats);
                return i2.compareTo(i1);
            }

        }));
        sortItems.add(new SortItem(5, "Least completed", Order.desc("Least completed"), new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Fabric f1 = (Fabric) o1;
                Fabric f2 = (Fabric) o2;
                List<Category> cats = categoryService.getAll();
                Integer i1 = f1.getCompleteness(cats);
                Integer i2 = f2.getCompleteness(cats);
                return i1.compareTo(i2);
            }

        }));
    }

    @Override
    public FabricFilter build() {
        createFilters();
        buildSortList();
        return this;
    }

    @Override
    protected void createFilters() {
//        addWeightFilter();
//        addWidthFilter();
//        addUsableWidthFilter();
//        
        FilterGroup data;
        /**
         * Add filters for every Category listed for filtering.
         */
        for (Tab tab : tabService.getAll()) {
            data = new FilterGroup(tab.getName());
            int numFilters = 0;
            for (Category category : tab.getCategories()) {
                if (category.isFilter()) {
                    data.addFilter(categoryService.getFilterFromCategory(category));
                    numFilters++;
                }
            }
            if (numFilters > 0) {
                addFilterGroup(data);
            }
        }
    }

    /**
     * Adds a filter for weight of the fabric.
     */
    private void addWeightFilter() {
        SliderFilter slider = new SliderFilter("Weight", null, "weight");
        slider.setUnit("g/m<sup>2</sup>");
        slider.setMaxValue(roundUp(getMaxWeight()));
        slider.setMaxSelectableValue(roundUp(getMaxWeight()));
        generalGroup.addFilter(slider);
    }

    private void addWidthFilter() {
        SliderFilter slider = new SliderFilter("Width", null, "width");
        slider.setUnit("m");
        slider.setMaxValue(roundUp(getMaxWidth()));
        slider.setMaxSelectableValue(roundUp(getMaxWidth()));
        widthGroup.addFilter(slider);
    }

    private void addUsableWidthFilter() {
        SliderFilter slider = new SliderFilter("UsableWidth", null, "usableWidth");
        slider.setUnit("m");
        slider.setMaxValue(roundUp(getMaxUsableWidth()));
        slider.setMaxSelectableValue(roundUp(getMaxUsableWidth()));
        widthGroup.addFilter(slider);
    }

    public String getSelectedSort() {
        return selectedSort;
    }

    public void setSelectedSort(String selectedSort) {
        this.selectedSort = selectedSort;
    }

    public List<SortItem> getSortItems() {
        return sortItems;
    }

    public void setSortItems(ArrayList<SortItem> sortItems) {
        this.sortItems = sortItems;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxUsableWidth() {
        return maxUsableWidth;
    }

    public void setMaxUsableWidth(int maxUsableWidth) {
        this.maxUsableWidth = maxUsableWidth;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public TabService getTabService() {
        return tabService;
    }

    public void setTabService(TabService tabService) {
        this.tabService = tabService;
    }

    @Override
    public Criteria useCriteria(Criteria criteria) {
        criteria = super.useCriteria(criteria);
        criteria.createCriteria("data", "dataAlias");
        criteria.addOrder(sortItems.get(Integer.parseInt(selectedSort)).getOrder());
        return criteria;
    }

    @Override
    public void filterList(List list) {
        super.filterList(list);
        list.sort(sortItems.get(Integer.parseInt(selectedSort)).getComparator());
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

}

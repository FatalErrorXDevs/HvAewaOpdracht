package nl.hva.filters;

import java.util.Comparator;
import org.hibernate.criterion.Order;

/**
 * The {@code SortItem} class represents the data for selecting a order. A SortItem extends the
 * {@code FilterItem} class. It adds a Order.
 *
 * @author Bert Kooij
 * @version 2.1
 * @since 2.1
 */
public class SortItem extends FilterItem {

    private Order order;
    
    private Comparator comparator;

    public SortItem(int id, String name, Order order, Comparator comparator) {
        this(id, name, order);
        this.comparator = comparator;
    }
    
    public SortItem(int id, String name, Order order) {
        this(id, name);
        this.order = order;
    }

    public SortItem(int id, String name) {
        super(id, name);
    }

    public void Sort() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }
}

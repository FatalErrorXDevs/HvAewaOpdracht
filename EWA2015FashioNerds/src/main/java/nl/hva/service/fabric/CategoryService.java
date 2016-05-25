package nl.hva.service.fabric;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.hva.dao.CategoryDAO;
import nl.hva.filters.interfaces.FilterAndOption;
import nl.hva.filters.Filter;
import nl.hva.filters.FilterWithItems;
import nl.hva.filters.SliderFilter;
import nl.hva.input.InputLoader;
import nl.hva.input.InputType;
import nl.hva.input.exception.InputNotImplementedException;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.CategoryMulti;
import nl.hva.model.fabric.CategorySingle;
import nl.hva.model.fabric.data.Data;
import nl.hva.model.fabric.data.DataNumber;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yassine
 */
@Service
@Transactional
@Scope("singleton")
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    public List<Category> getFilters() {
        return categoryDAO.getFilters();
    }

    public List<CategorySingle> getAllCategoriesSingle() {
        return categoryDAO.getAllCategoriesSingle();
    }

    public Category getByName(String name) {
        return categoryDAO.getByName(name);
    }

    public Category getById(int id) {
        return categoryDAO.getById(id);
    }

    public void save(Category category) {
        categoryDAO.save(category);
    }

    public void update(Category category) {
        categoryDAO.update(category);
    }

    public void delete(Category category) {
        categoryDAO.delete(category);
    }

    public int getMaxValueByCategory(Category cat) {
        int high = 0;
        for (Data data : cat.getData()) {
            if (data instanceof DataNumber) {
                int value = ((DataNumber) data).getValueNum();
                high = (value > high) ? value : high;
            }
        }
        return high;
    }

    public Filter getFilterFromCategory(Category category) {
        try {
            Filter filter = InputLoader.getInput(category.getDataType()).getFilter();
            filter.setId(category.getName() + category.getId());
            filter.setName(category.getName());
            filter.setDescription(category.getDescription());
            filter.setFieldName("data.value");
            filter.setCategoryId(category.getId());
            filter.setUnit(category.getUnit());
            if (category instanceof CategoryMulti) {
                for (Data data : category.getData()) {
                    ((FilterWithItems) filter).addItem(data.getId(), data.getValue());
                }
            }
            if (category.getDataType() == InputType.CHECK) {
                ((FilterAndOption) filter).setAndOption(true);
            }
            if (category.getDataType() == InputType.NUMBER) {
                int num = roundUp(getMaxValueByCategory(category));
                ((SliderFilter) filter).setMaxSelectableValue(num);
                ((SliderFilter) filter).setMaxValue(num);
            }

            return filter;
        } catch (InputNotImplementedException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Rounds a int up to a 100 value.
     *
     * @param n
     * @return
     */
    private int roundUp(int n) {
        return ((n + 99) / 100) * 100;
    }
}

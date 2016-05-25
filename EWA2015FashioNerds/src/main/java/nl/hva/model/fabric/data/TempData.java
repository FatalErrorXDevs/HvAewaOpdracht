package nl.hva.model.fabric.data;

/**
 *
 * @author Bert
 */
public class TempData {

    private int categoryId;
    private String value;
    private int dataId;

    public TempData(int categoryId) {
        this.categoryId = categoryId;
    }

    public TempData() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }
    
}

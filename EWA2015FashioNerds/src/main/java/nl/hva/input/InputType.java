package nl.hva.input;

/**
 *
 * @author Bert
 */
public enum InputType {
    
    RADIO(1, true),
    SELECT(2, true),
    CHECK(3,true),
    TEXT(4),
    LARGE_TEXT(5),
    NUMBER(6),
    COLOR(7);

    private final int id;
    
    private boolean multipleItems = false;

    private InputType(int id) {
        this.id = id;
    }

    private InputType(int id, boolean multipleItems) {
        this.id = id;
        this.multipleItems = multipleItems;
    }

    public boolean isMultipleItems() {
        return multipleItems;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "" + id;
    }

    public static InputType parse(int id) {
        InputType input = null; // Default
        for (InputType item : InputType.values()) {
            if (item.getId() == id) {
                input = item;
                break;
            }
        }
        return input;
    }

}

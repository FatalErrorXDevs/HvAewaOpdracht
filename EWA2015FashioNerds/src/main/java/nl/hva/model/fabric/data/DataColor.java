package nl.hva.model.fabric.data;

import javax.persistence.Entity;

/**
 *
 * @author Bert
 */
@Entity
public class DataColor extends DataSingle {

    private int r;
    private int g;
    private int b;

    @Override
    public void setValue(String value) {
        super.setValue(value);
        int[] rgb = parseRGB(value);
        setR(rgb[0]);
        setG(rgb[1]);
        setB(rgb[2]);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }    

    public static int[] parseRGB(String Value) {
        String rgbOnly = Value.substring(Value.indexOf("(")+1, Value.length() - 1);
        String[] colors = rgbOnly.split(",");

        int[] rgb = new int[3];
        int i = 0;
        for (String color : colors) {
            rgb[i] = Integer.valueOf(color);
            i++;
        }
        return rgb;
    }
}

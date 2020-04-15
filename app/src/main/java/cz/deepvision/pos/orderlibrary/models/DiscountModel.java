package cz.deepvision.pos.orderlibrary.models;

public class DiscountModel {
    private String description;
    private double value;
    private boolean counted;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }
}

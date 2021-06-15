package Rewards;

public abstract class Badge {
    protected String name;
    protected double discountPercentage;

    protected Badge(){

    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public String getName() {
        return name;
    }
}

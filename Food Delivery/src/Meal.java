public class Meal extends MenuItem {

    private int extraToppings;

    public Meal(String name, double basePrice, int extraToppings) {
        super(name, basePrice);
        this.extraToppings = extraToppings;
    }

    @Override
    public double getPrice() {
        // each extra topping costs 0.50
        return getBasePrice() + extraToppings * 0.5;
    }
}

public class Drink extends MenuItem {

    private boolean large;

    public Drink(String name, double basePrice, boolean large) {
        super(name, basePrice);
        this.large = large;
    }

    @Override
    public double getPrice() {
        // large drinks cost 1.00 more
        if (large) {
            return getBasePrice() + 1.0;
        }
        return getBasePrice();
    }
}

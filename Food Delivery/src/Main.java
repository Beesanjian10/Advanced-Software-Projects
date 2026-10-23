public class Main {

    public static void main(String[] args) {
        // menu items
        MenuItem burger = new Meal("Burger", 8.0, 2);
        MenuItem pizza = new Meal("Pizza", 10.0, 0);
        MenuItem cola = new Drink("Cola", 2.0, true);
        MenuItem water = new Drink("Water", 1.0, false);

        // order 1: standard delivery, paid by card
        Order order1 = new Order("Beesan", new StandardDelivery());
        order1.addItem(burger);
        order1.addItem(cola, 2);
        order1.addItem(water);
        System.out.println(order1);
        order1.pay(new CardPayment());
        order1.deliver();
        System.out.println();

        // order 2: express delivery, paid by cash
        Order order2 = new Order("Sara", new ExpressDelivery());
        order2.addItem(pizza, 2);
        order2.addItem(cola);
        System.out.println(order2);
        order2.pay(new CashPayment());
        order2.pay(new CashPayment()); // trying to pay twice
        order2.deliver();
        System.out.println();

        // order 3: cancelled before payment
        Order order3 = new Order("Omar", new StandardDelivery());
        order3.addItem(burger, 3);
        order3.cancel();
        order3.deliver(); // should not work
        System.out.println(order3);
    }
}

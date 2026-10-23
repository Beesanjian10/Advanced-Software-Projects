public class CardPayment implements PaymentMethod {

    private static final double FEE = 0.02;

    @Override
    public double pay(double amount) {
        // card payments have a 2% fee
        return amount + amount * FEE;
    }
}

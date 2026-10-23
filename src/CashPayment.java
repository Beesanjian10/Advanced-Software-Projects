public class CashPayment implements PaymentMethod {

    @Override
    public double pay(double amount) {
        // no extra charge for cash
        return amount;
    }
}

public interface PaymentMethod {
    // returns the amount that is actually charged
    double pay(double amount);
}

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static int counter = 1000;

    private int orderNumber;
    private String customerName;
    private List<OrderLine> lines;
    private OrderStatus status;
    private DeliveryOption delivery;

    public Order(String customerName, DeliveryOption delivery) {
        counter++;
        this.orderNumber = counter;
        this.customerName = customerName;
        this.delivery = delivery;
        this.lines = new ArrayList<>();
        this.status = OrderStatus.NEW;
    }

    public void addItem(MenuItem item) {
        addItem(item, 1);
    }

    public void addItem(MenuItem item, int qty) {
        if (status != OrderStatus.NEW) {
            System.out.println("Can't add items, order is already " + status);
            return;
        }
        if (qty <= 0) {
            System.out.println("Quantity must be at least 1");
            return;
        }
        lines.add(new OrderLine(item, qty));
    }

    public double getSubtotal() {
        double sum = 0;
        for (OrderLine line : lines) {
            sum += line.getLineTotal();
        }
        return sum;
    }

    public double getTotal() {
        return getSubtotal() + delivery.getFee();
    }

    public void pay(PaymentMethod method) {
        if (status != OrderStatus.NEW) {
            System.out.println("Order " + orderNumber + " can't be paid (status: " + status + ")");
            return;
        }
        if (lines.isEmpty()) {
            System.out.println("Order " + orderNumber + " is empty, nothing to pay");
            return;
        }
        double charged = method.pay(getTotal());
        System.out.printf("Order %d paid, charged: %.2f%n", orderNumber, charged);
        status = OrderStatus.PAID;
    }

    public void deliver() {
        if (status == OrderStatus.PAID) {
            status = OrderStatus.DELIVERED;
            System.out.println("Order " + orderNumber + " delivered to " + customerName);
        } else {
            System.out.println("Order " + orderNumber + " must be paid before delivery");
        }
    }

    public void cancel() {
        if (status == OrderStatus.DELIVERED) {
            System.out.println("Order " + orderNumber + " was already delivered, can't cancel");
        } else {
            status = OrderStatus.CANCELLED;
            System.out.println("Order " + orderNumber + " cancelled");
        }
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderNumber)
          .append(" for ").append(customerName)
          .append(" [").append(status).append("]\n");
        for (OrderLine line : lines) {
            sb.append(String.format("  %dx %-12s %6.2f%n",
                    line.getQuantity(), line.getItem().getName(), line.getLineTotal()));
        }
        sb.append(String.format("  Subtotal: %.2f%n", getSubtotal()));
        sb.append(String.format("  Delivery: %.2f%n", delivery.getFee()));
        sb.append(String.format("  Total:    %.2f", getTotal()));
        return sb.toString();
    }
}

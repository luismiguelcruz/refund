package model;

public class Transaction {
    private final int id;
    private final String paymentReference;
    private final double amountCharged;

    public Transaction(int id, String paymentReference, double amountCharged) {
        this.id = id;
        this.paymentReference = paymentReference;
        this.amountCharged = amountCharged;
    }

    public int getId() {
        return id;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public double getAmountCharged() {
        return amountCharged;
    }
}

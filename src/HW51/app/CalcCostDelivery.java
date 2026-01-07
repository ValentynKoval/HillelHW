package HW51.app;

public class CalcCostDelivery extends CalcCostBase {
    private final static double deliveryPrice = 7d;

    @Override
    public double calcCost(Product product) {
        return super.calcCost(product)
                + deliveryPrice;
    }
}

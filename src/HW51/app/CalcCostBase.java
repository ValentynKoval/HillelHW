package HW51.app;

public class CalcCostBase {
    public double calcCost(Product product) {
        return product.getQuota() * product.getPrice();
    }
}

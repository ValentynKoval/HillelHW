package HW52.app;

public class CalcRevenueTaxed extends CalcRevenueBase{
    private  double TAX_RATE = 5;

    @Override
    public double calcRevenue(Sale sale) {
        return super.calcRevenue(sale) * (1 - TAX_RATE / 100);
    }
}

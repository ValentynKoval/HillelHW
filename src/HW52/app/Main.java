package HW52.app;

public class Main {
    public static void main(String[] args) {

        String[] data = getData();

        Sale sale = new Sale(Integer.parseInt(data[0]),
                Double.parseDouble(data[1]));

        CalcRevenueBase revenueBase = new CalcRevenueBase();
        double baseRevenue = revenueBase.calcRevenue(sale);

        CalcRevenueTaxed revenueTaxed = new CalcRevenueTaxed();
        double taxedRevenue = revenueTaxed.calcRevenue(sale);

        String baseOutput = sale + "\nRevenue is " +
                Constants.CURRENCY + " " + baseRevenue + ".";
        String taxedOutput = sale + "\nRevenue taxed is " +
                Constants.CURRENCY + " " + taxedRevenue + ".";

        getOutput(baseOutput);
        getOutput(taxedOutput);
    }

    // Набір вхідних даних
    public static String[] getData() {
        return new String[] { "120", "4.5"};
    }

    public static void getOutput(String output) {
        System.out.println(output);
    }
}

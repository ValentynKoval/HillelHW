package HW42.app;

public class OutputFormatter {
    public String getOutputData(Buyer buyer) {
        return "Buyer: " + buyer.getFirstName() + " " + buyer.getLastName() + ", phone " + buyer.getPhone();
    }
}

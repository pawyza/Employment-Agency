package managers.loggedEmployeesManager.addEmployee.formerPositionAdding;

public class Positions {
    private String position;
    private String trade;

    public Positions(String position, String trade) {
        this.position = position;
        this.trade = trade;
    }

    public String getPosition() {
        return position;
    }

    public String getTrade() {
        return trade;
    }

}

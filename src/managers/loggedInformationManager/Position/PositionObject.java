package managers.loggedInformationManager.Position;

public class PositionObject {
    private String position;
    private String  trade;

    public PositionObject(String position, String trade) {
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

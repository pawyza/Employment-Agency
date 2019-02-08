package managers.offerBrowser.fullInformation;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import managers.offerBrowser.OfferObject;

import java.util.ArrayList;

public class OfferViewController {

    public void prepare(OfferObject offerObject) {
        txtPosition.setText(offerObject.getPosition());
        txtTrade.setText(offerObject.getTrade());
        txtCompanyName.setText(offerObject.getCompanyName());
        txtCountry.setText(offerObject.getCountry());
        txtCity.setText(offerObject.getCity());
        txtOfferType.setText(offerObject.getOfferType());
        txtVacancy.setText(String.valueOf(offerObject.getVacancy()));
        txtSalary.setText(offerObject.getSalary().toString() +" " + offerObject.getCurrency());
        txtLanguages.setText(prep(offerObject.getLanguages()));
        txtPermissions.setText(prep(offerObject.getPermissions()));
        txtDetails.setText(prep(offerObject.getDetails()));
        txtEmployees.setText(offerObject.getEmployeesID());
    }

    @FXML
    private Text txtPosition;

    @FXML
    private Text txtTrade;

    @FXML
    private Text txtCompanyName;

    @FXML
    private Text txtCountry;

    @FXML
    private Text txtCity;

    @FXML
    private Text txtEmployees;

    @FXML
    private Text txtOfferType;

    @FXML
    private Text txtVacancy;

    @FXML
    private Text txtSalary;

    @FXML
    private Text txtLanguages;

    @FXML
    private Text txtPermissions;

    @FXML
    private Text txtDetails;


    private String prep(ArrayList<String> list){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String s:list) {
            sb.append(s+", ");
            if(sb.length()>50*i){
                sb.append("\n");
                i++;
            }
        }
        if(sb.length()>2)
            sb.delete(sb.length()-2,sb.length());
        else
            sb.append("BRAK");
        return sb.toString();
    }
}

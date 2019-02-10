package managers.loggedEmployersManager.offerAdd.resultView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import managers.Switcher;
import managers.loggedEmployersManager.offerAdd.NewOfferObject;

import java.util.ArrayList;

public class OfferAddingResultController {

    NewOfferObject newOfferObject;

    public void prepare(NewOfferObject newOfferObject){
        this.newOfferObject = newOfferObject;
            txtPosition.setText(newOfferObject.getPosition());
            txtCompanyName.setText(newOfferObject.getCompanyName());
            txtCountry.setText(newOfferObject.getCountry());
            txtCity.setText(newOfferObject.getCity());
            txtCountry.setText(newOfferObject.getCountry());
            txtOfferType.setText(String.valueOf(newOfferObject.getOfferType()));
            txtVacancy.setText(String.valueOf(newOfferObject.getVacancy()));
            txtSalary.setText(newOfferObject.getSalary().toString() +" " +newOfferObject.getCurrency());
            txtLanguages.setText(prep(newOfferObject.getLanguages()));
            txtPermissions.setText(prep(newOfferObject.getPermissions()));
            txtDetails.setText(prep(newOfferObject.getDetails()));
    }


    @FXML
    private Text txtPosition;

    @FXML
    private Text txtCompanyName;

    @FXML
    private Text txtCountry;

    @FXML
    private Text txtCity;

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

    @FXML
    private Button btnAdd;

    @FXML
    void onAbort(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployersManager/employersManagerPanel.fxml");
    }

    @FXML
    void onAdd(ActionEvent event) {
        new OfferAdder().add(newOfferObject);
        btnAdd.setDisable(true);
    }

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

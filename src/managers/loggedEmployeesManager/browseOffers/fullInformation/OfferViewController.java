package managers.loggedEmployeesManager.browseOffers.fullInformation;

import java.util.ArrayList;

public class OfferViewController {

    private String prep(ArrayList<String> list){
        StringBuilder sb = new StringBuilder();
        for (String s:list) {
            sb.append(s+", ");
        }
        if(sb.length()>2)
            sb.delete(sb.length()-2,sb.length());
        return sb.toString();
    }
}

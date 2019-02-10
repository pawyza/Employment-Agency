package managers.loggedEmployersManager.offerAdd.resultView;

import managers.LoggedUser;
import managers.loggedEmployersManager.offerAdd.NewOfferObject;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferAdder {
    public void add(NewOfferObject offer){
        try {
            CallableStatement cs = LoggedUser.getConnection().prepareCall("{call sp_INSERT_OFERTA (?,?,?,?,?,?,?,?)}");
            cs.setString(1,offer.getPosition());
            cs.setString(2,offer.getCompanyName());
            cs.setString(3,offer.getCountry());
            cs.setString(4,offer.getCity().toUpperCase());
            cs.setString(5,offer.getOfferType());
            cs.setInt(6,offer.getVacancy());
            cs.setBigDecimal(7,offer.getSalary());
            cs.setString(8,offer.getCurrency());
            cs.execute();
            ResultSet resultSet = LoggedUser.getConnection().prepareStatement("SELECT IDENT_CURRENT('OFERTY') AS ID").executeQuery();
            resultSet.next();
            int id = Integer.parseInt(resultSet.getString(resultSet.getMetaData().getColumnName(1)));
            for (String l:offer.getDetails()) {
                cs = LoggedUser.getConnection().prepareCall("{call sp_INSERT_OFERTA_DETAL (?,?)}");
                cs.setInt(1,id);
                cs.setString(2,l);
                cs.execute();
            }
            for (String p:offer.getLanguages()) {
                cs = LoggedUser.getConnection().prepareCall("{ call sp_INSERT_OFERTA_JEZYK  (?,?)}");
                cs.setInt(1,id);
                cs.setString(2,p);
                cs.execute();
            }
            for (String p:offer.getPermissions()) {
                cs = LoggedUser.getConnection().prepareCall("{ call sp_INSERT_OFERTA_UPRAWNIENIA (?,?)}");
                cs.setInt(1,id);
                cs.setString(2,p);
                cs.execute();
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Blad serwera przy zapisie");
        }
    }
}

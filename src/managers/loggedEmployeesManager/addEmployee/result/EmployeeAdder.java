package managers.loggedEmployeesManager.addEmployee.result;

import managers.LoggedUser;
import managers.loggedEmployeesManager.addEmployee.DataContainer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeAdder {
    private DataContainer dataContainer;
    public EmployeeAdder(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public void add() throws RuntimeException {
        try {
            CallableStatement cs = LoggedUser.getConnection().prepareCall("{call sp_INSERT_PRACOWNIK (?,?,?,?,?,?)}");
            cs.setString(1,dataContainer.getName().toUpperCase());
            cs.setString(2,dataContainer.getForename().toUpperCase());
            cs.setString(3,dataContainer.getBirthDate());
            cs.setString(4,dataContainer.getCountry());
            cs.setString(5,dataContainer.getNativeLanguage());
            cs.setLong(6,dataContainer.getNumber());
            cs.execute();
            ResultSet resultSet = LoggedUser.getConnection().prepareStatement("SELECT IDENT_CURRENT('PRACOWNICY') AS ID").executeQuery();
            resultSet.next();
            int id = Integer.parseInt(resultSet.getString(resultSet.getMetaData().getColumnName(1)));
            for (String l:dataContainer.getAdditionalLanguages()) {
                cs = LoggedUser.getConnection().prepareCall("{call sp_INSERT_PRACOWNIK_JEZYK (?,?)}");
                cs.setInt(1,id);
                cs.setString(2,l);
                cs.execute();
            }
            for (String p:dataContainer.getEmployeePermissions()) {
                cs = LoggedUser.getConnection().prepareCall("{ call sp_INSERT_PRACOWNIK_UPRAWNIENIA  (?,?)}");
                cs.setInt(1,id);
                cs.setString(2,p);
                cs.execute();
            }
            for (String p:dataContainer.getFormerPositions()) {
                cs = LoggedUser.getConnection().prepareCall("{ call sp_INSERT_PRACOWNIK_POPRZEDNIE_STANOWISKA (?,?)}");
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

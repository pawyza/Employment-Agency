package managers.loggedEmployeesManager.addEmployee;

import managers.LoggedUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DataContainer {
    private ArrayList<String> languages = new ArrayList<>();
    private ArrayList<String> trade = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private ArrayList<String> countries = new ArrayList<>();

    public DataContainer() throws SQLException {
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT JEZYK FROM JEZYKI");
        while (resultSet.next()){
            languages.add(resultSet.getString("JEZYK"));
        }
        resultSet = statement.executeQuery("SELECT BRANZA FROM BRANZE");
        while (resultSet.next()){
            trade.add(resultSet.getString("BRANZA"));
        }
        resultSet = statement.executeQuery("SELECT KRAJ FROM KRAJE");
        while (resultSet.next()){
            countries.add(resultSet.getString("KRAJ"));
        }
        resultSet = statement.executeQuery("SELECT NAZWA_UPRAWNIENIA FROM UPRAWNIENIA");
        while (resultSet.next()){
            permissions.add(resultSet.getString("NAZWA_UPRAWNIENIA"));
        }
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public ArrayList<String> getTrade() {
        return trade;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }
    //Informations to add

    private String name;
    private String forename;
    private long number;
    private String birthDate;
    private String country;
    private String nativeLanguage;

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException{
        if(name.length()<=30 && name.length()!=0 && !Pattern.compile( "[0-9]" ).matcher(name).find()) {
            this.name = name;
        }
        else {
        throw new IllegalArgumentException();
        }
    }

    public String getForename() {
        return forename;

    }

    public void setForename(String forename) throws IllegalArgumentException{
        if(forename.length()<=30 && forename.length()!=0 && !Pattern.compile( "[0-9]" ).matcher(forename).find()) {
        this.forename = forename;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if(number.length()<=18 && number.length()>4) {
            if(Integer.parseInt(number)>0)
            this.number = Integer.parseInt(number);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws IllegalArgumentException {
        if (birthDate.isAfter(LocalDate.of(1920,1,1)) && birthDate.isBefore(LocalDate.of(2005,1,1))){
        this.birthDate = birthDate.format(DateTimeFormatter.ofPattern("MM/dd/YYYY"));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws IllegalArgumentException{
        if (country.length()>0) {
        this.country = country;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) throws IllegalArgumentException{
        if (nativeLanguage.length()>0) {
            this.nativeLanguage = nativeLanguage;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private ArrayList<String> additionalLanguages = new ArrayList<>();

    public ArrayList<String> getAdditionalLanguages() {
        return additionalLanguages;
    }

    public void setAdditionalLanguages(ArrayList<String> additionalLanguages) {
        this.additionalLanguages = additionalLanguages;
    }

    private ArrayList<String> formerPositions = new ArrayList<>();

    public ArrayList<String> getFormerPositions() {
        return formerPositions;
    }

    public void setFormerPositions(ArrayList<String> formerPositions) {
        this.formerPositions = formerPositions;
    }

    private ArrayList<String> employeePermissions = new ArrayList<>();

    public ArrayList<String> getEmployeePermissions() {
        return employeePermissions;
    }

    public void setEmployeePermissions(ArrayList<String> employeePermissions) {
        this.employeePermissions = employeePermissions;
    }
}

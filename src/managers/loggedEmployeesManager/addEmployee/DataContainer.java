package managers.loggedEmployeesManager.addEmployee;

import javax.swing.text.DateFormatter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class DataContainer {
    private ArrayList<String> languages = new ArrayList<>();
    private ArrayList<String> position = new ArrayList<>();
    private ArrayList<String> trade = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private ArrayList<String> countries = new ArrayList<>();
    private ArrayList<Integer> offer = new ArrayList<>();

    public DataContainer(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT JEZYK FROM JEZYKI");
        while (resultSet.next()){
            languages.add(resultSet.getString("JEZYK"));
        }
        resultSet = statement.executeQuery("SELECT STANOWISKO FROM STANOWISKA");
        while (resultSet.next()){
            position.add(resultSet.getString("STANOWISKO"));
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
        resultSet = statement.executeQuery("SELECT ID_OFERTY FROM OFERTY");
        while (resultSet.next()){
            offer.add(resultSet.getInt("ID_OFERTY"));
        }
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public ArrayList<String> getPosition() {
        return position;
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

    public ArrayList<Integer> getOffer() {
        return offer;
    }

    //Informations to add

    private String name;
    private String forname;
    private int number;
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

    public String getForname() {
        return forname;

    }

    public void setForname(String forname) throws IllegalArgumentException{
        if(forname.length()<=30 && forname.length()!=0 && !Pattern.compile( "[0-9]" ).matcher(forname).find()) {
        this.forname = forname;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public int getNumber() {
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
        this.birthDate = birthDate.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));
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

    private ArrayList<String> additionalLanguages;

    public ArrayList<String> getAdditionalLanguages() {
        return additionalLanguages;
    }

    public void setAdditionalLanguages(ArrayList<String> additionalLanguages) {
        this.additionalLanguages = additionalLanguages;
    }

    private ArrayList<String> formerPositions;

    public ArrayList<String> getFormerPositions() {
        return formerPositions;
    }

    public void setFormerPositions(ArrayList<String> formerPositions) {
        this.formerPositions = formerPositions;
    }

}

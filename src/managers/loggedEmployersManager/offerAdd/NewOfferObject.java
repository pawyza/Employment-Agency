package managers.loggedEmployersManager.offerAdd;

import java.math.BigDecimal;
import java.util.ArrayList;

public class NewOfferObject {
    private String position;
    private String companyName;
    private String country;
    private String city;
    private String offerType;
    private int vacancy;
    private BigDecimal salary;
    private String currency;
    private ArrayList<String> details;
    private ArrayList<String> permissions;
    private ArrayList<String> languages;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws IllegalArgumentException{
        if(!city.equals("")){
            this.city = city.toUpperCase();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) throws IllegalArgumentException{
        if(salary.intValue()>0) {
            this.salary = salary;
        } else
            throw new IllegalArgumentException();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }
}

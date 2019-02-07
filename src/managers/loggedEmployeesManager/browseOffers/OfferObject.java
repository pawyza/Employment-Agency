package managers.loggedEmployeesManager.browseOffers;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OfferObject {
    private int id;
    private String position;
    private String trade;
    private String companyName;
    private String country;
    private String city;
    private String offerType;
    private int vacancy;
    private BigDecimal salary;
    private String currency;
    private ArrayList<String> languages;
    private String languagesRecord;
    private ArrayList<String> details;
    private String detailsRecord;
    private ArrayList<String> permissions;
    private String permissionsRecord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
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

    public void setCity(String city) {
        this.city = city;
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

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
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

    public String getLanguagesRecord() {
        return languagesRecord;
    }

    public String getDetailsRecord() {
        return detailsRecord;
    }

    public String getPermissionsRecord() {
        return permissionsRecord;
    }
}

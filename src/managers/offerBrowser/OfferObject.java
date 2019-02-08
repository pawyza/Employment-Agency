package managers.offerBrowser;

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
    private ArrayList<String> details;
    private ArrayList<String> permissions;
    private ArrayList<Integer> employeesID;

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

    public String getEmployeesID() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int s:employeesID) {
            sb.append(s+", ");
            if(sb.length()>20*i){
                sb.append("\n");
                i++;
            }
        }
        if(sb.length()>0)
            sb.delete(sb.length()-2,sb.length());
        else
            sb.append("BRAK");
        return sb.toString();
    }

    public void setEmployeesID(ArrayList<Integer> employeesID) {
        this.employeesID = employeesID;
    }
}

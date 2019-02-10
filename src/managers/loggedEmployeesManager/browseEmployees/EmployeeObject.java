package managers.loggedEmployeesManager.browseEmployees;

import java.util.ArrayList;

public class EmployeeObject {
    private int id;
    private String name;
    private String forename;
    private long number;
    private String birthDate;
    private String country;
    private String nativeLanguage;
    private ArrayList<String> formerPositions = new ArrayList<>();
    private ArrayList<String> employeePermissions = new ArrayList<>();
    private ArrayList<String> allLanguages = new ArrayList<>();
    private String formerPositionsRecord;
    private String employeePermissionsRecord;
    private String allLanguagesRecord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getFormerPositionsRecord() {
        return formerPositionsRecord;
    }

    public String getEmployeePermissionsRecord() {
        return employeePermissionsRecord;
    }

    public String getAllLanguagesRecord() {
        return allLanguagesRecord;
    }

    public ArrayList<String> getAllLanguages() {
        return allLanguages;
    }

    public void setAllLanguages(ArrayList<String> allLanguages) {
        this.allLanguages = allLanguages;
        allLanguagesRecord = prep(allLanguages);
    }

    public ArrayList<String> getFormerPositions() {
        return formerPositions;
    }

    public void setFormerPositions(ArrayList<String> formerPositions) {
        this.formerPositions = formerPositions;
        formerPositionsRecord = prep(formerPositions);
    }


    public ArrayList<String> getEmployeePermissions() {
        return employeePermissions;
    }

    public void setEmployeePermissions(ArrayList<String> employeePermissions) {
        this.employeePermissions = employeePermissions;
        employeePermissionsRecord = prep(employeePermissions);
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

package managers.loggedEmployersManager.employersBrowser;

import java.math.BigInteger;

public class EmployerObject {

    private int id;
    private String name;
    private String country;
    private String number;
    private String mail;
    private String idNumber;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if(!number.equals("0")) {
            this.number = number;
        }
        else {
            this.number = "NO NUMBER";
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}

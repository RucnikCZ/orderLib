package cz.deepvision.pos.orderlibrary.models;


import java.util.ArrayList;

import cz.deepvision.pos.orderlibrary.utils.EnumUtil;

public class UserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String password;
    private EnumUtil.UserRole role;
    private String externalID;
    private Double startMoney;
    private String domain;
    private ArrayList<String> branches;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnumUtil.UserRole getRole() {
        return role;
    }

    public void setRole(EnumUtil.UserRole role) {
        this.role = role;
    }

    public String getExternalID() {
        return externalID;
    }

    public void setExternalID(String externalID) {
        this.externalID = externalID;
    }
    
    public Double getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(Double startMoney) {
        this.startMoney = startMoney;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public ArrayList<String> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<String> branches) {
        this.branches = branches;
    }
}

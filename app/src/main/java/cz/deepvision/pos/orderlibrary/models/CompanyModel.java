package cz.deepvision.pos.orderlibrary.models;

public class CompanyModel {
    private String logoURL="";
    private String brandName="";
    private String companyName="";
    private String address="";
    private String identificator="";
    private String vatIdentificator="";
    private String establishmentID="";
    private String posID="";
    private String crewID="";


    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public String getVatIdentificator() {
        return vatIdentificator;
    }

    public void setVatIdentificator(String vatIdentificator) {
        this.vatIdentificator = vatIdentificator;
    }

    public String getEstablishmentID() {
        return establishmentID;
    }

    public void setEstablishmentID(String establishmentID) {
        this.establishmentID = establishmentID;
    }

    public String getPosID() {
        return posID;
    }

    public void setPosID(String posID) {
        this.posID = posID;
    }

    public String getCrewID() {
        return crewID;
    }

    public void setCrewID(String crewID) {
        this.crewID = crewID;
    }

    @Override
    public String toString() {
        return
                "brandName='" + brandName + '\'' +
                ", companyName='" + companyName + '\'';
    }
}

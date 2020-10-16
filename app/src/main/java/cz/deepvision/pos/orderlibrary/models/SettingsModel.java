package cz.deepvision.pos.orderlibrary.models;

import java.util.ArrayList;

import cz.deepvision.pos.orderlibrary.utils.EnumUtil;

public class SettingsModel {
    private EnumUtil.PriceType deliveryType = EnumUtil.PriceType.DELIVERY;
    private ArrayList<CompanyModel> company;
    private boolean enigooEnabled = false;
    private boolean speedloEnabled = false;
    private boolean customDataEnabled = false;
    private boolean autoPrintEnabled = false;
    private boolean printRecipeQR = false;
    private boolean printKitchenTicket = false;
    private boolean printRecipeLogo = false;
    //private boolean czechEETEnabled = false;
    private boolean printKitchenTicket2Times = false;
    private boolean printRecipe2Times = false;
    private boolean printProductCodes = false;
    private boolean printOrderOrigin = false;
    private boolean darkThemeMode = false;
    private boolean devMode = false;
    private boolean printCategories = false;
    private boolean printCoverBon = false;
    private boolean automaticFinishOrder = true;
    private boolean invertBonColors = false;
    private boolean printer58mm = false;
    private boolean printer80mm = false;


    private Integer lastUsedSelectorID = 0;

    private UserModel enigooUser;
    private UserModel speedloUser;


    public EnumUtil.PriceType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(EnumUtil.PriceType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public boolean isPrinter58mm() {
        return printer58mm;
    }

    public void setPrinter58mm(boolean printer58mm) {
        this.printer58mm = printer58mm;
    }

    public boolean isPrinter80mm() {
        return printer80mm;
    }

    public void setPrinter80mm(boolean printer80mm) {
        this.printer80mm = printer80mm;
    }

    public ArrayList<CompanyModel> getCompany() {
        return company;
    }

    public void setCompany(ArrayList<CompanyModel> company) {
        this.company = company;
    }

    public boolean isEnigooEnabled() {
        return enigooEnabled;
    }

    public void setEnigooEnabled(boolean enigooEnabled) {
        this.enigooEnabled = enigooEnabled;
    }

    public boolean isSpeedloEnabled() {
        return speedloEnabled;
    }

    public void setSpeedloEnabled(boolean speedloEnabled) {
        this.speedloEnabled = speedloEnabled;
    }

    public boolean isCustomDataEnabled() {
        return customDataEnabled;
    }

    public void setCustomDataEnabled(boolean customDataEnabled) {
        this.customDataEnabled = customDataEnabled;
    }

    public boolean isPrintOrderOrigin() {
        return printOrderOrigin;
    }

    public void setPrintOrderOrigin(boolean printOrderOrigin) {
        this.printOrderOrigin = printOrderOrigin;
    }

    public UserModel getEnigooUser() {
        return enigooUser;
    }

    public void setEnigooUser(UserModel enigooUser) {
        this.enigooUser = enigooUser;
    }

    public UserModel getSpeedloUser() {
        return speedloUser;
    }

    public void setSpeedloUser(UserModel speedloUser) {
        this.speedloUser = speedloUser;
    }

    public boolean isAutoPrintEnabled() {
        return autoPrintEnabled;
    }

    public void setAutoPrintEnabled(boolean autoPrintEnabled) {
        this.autoPrintEnabled = autoPrintEnabled;
    }

    public boolean isPrintRecipeQR() {
        return printRecipeQR;
    }

    public void setPrintRecipeQR(boolean printRecipeQR) {
        this.printRecipeQR = printRecipeQR;
    }

    public boolean isPrintKitchenTicket() {
        return printKitchenTicket;
    }

    public void setPrintKitchenTicket(boolean printKitchenTicket) {
        this.printKitchenTicket = printKitchenTicket;
    }

    public boolean isPrintRecipeLogo() {
        return printRecipeLogo;
    }

    public void setPrintRecipeLogo(boolean printRecipeLogo) {
        this.printRecipeLogo = printRecipeLogo;
    }


    public boolean isPrintKitchenTicket2Times() {
        return printKitchenTicket2Times;
    }

    public void setPrintKitchenTicket2Times(boolean printKitchenTicket2Times) {
        this.printKitchenTicket2Times = printKitchenTicket2Times;
    }

    public boolean isPrintRecipe2Times() {
        return printRecipe2Times;
    }

    public void setPrintRecipe2Times(boolean printRecipe2Times) {
        this.printRecipe2Times = printRecipe2Times;
    }

    public boolean isPrintProductCodes() {
        return printProductCodes;
    }
    public void setPrintProductCodes(boolean printProductCodes) {
        this.printProductCodes = printProductCodes;
    }

    public boolean isDarkThemeMode() {
        return darkThemeMode;
    }

    public void setDarkThemeMode(boolean darkThemeMode) {
        this.darkThemeMode = darkThemeMode;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }


    public boolean isPrintCategories() {
        return printCategories;
    }

    public void setPrintCategories(boolean printCategories) {
        this.printCategories = printCategories;
    }

    public boolean isPrintCoverBon() {
        return printCoverBon;
    }

    public void setPrintCoverBon(boolean printCoverBon) {
        this.printCoverBon = printCoverBon;
    }

    public Integer getLastUsedSelectorID() {
        return lastUsedSelectorID;
    }

    public void setLastUsedSelectorID(Integer lastUsedSelectorID) {
        this.lastUsedSelectorID = lastUsedSelectorID;
    }

    public boolean isAutomaticFinishOrder() {
        return automaticFinishOrder;
    }

    public void setAutomaticFinishOrder(boolean automaticFinishOrder) {
        this.automaticFinishOrder = automaticFinishOrder;
    }


    public boolean isInvertBonColors() {
        return invertBonColors;
    }

    public void setInvertBonColors(boolean invertBonColors) {
        this.invertBonColors = invertBonColors;
    }
}
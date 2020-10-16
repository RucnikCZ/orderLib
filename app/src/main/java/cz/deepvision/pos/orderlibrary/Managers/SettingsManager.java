package cz.deepvision.pos.orderlibrary.Managers;

import android.content.Context;

import java.util.ArrayList;

import cz.deepvision.pos.orderlibrary.models.CompanyModel;
import cz.deepvision.pos.orderlibrary.models.SettingsModel;
import cz.deepvision.pos.orderlibrary.models.UserModel;
import cz.deepvision.pos.orderlibrary.utils.EnumUtil;

public class SettingsManager {
    private static SettingsManager mSettingsManager = new SettingsManager();
    private static String TAG = "";
    private static Context ctx;
    private SettingsModel settings;


    private SettingsManager() {
    }

    public static SettingsManager getInstance() {
        return mSettingsManager;
    }


    public void init(SettingsModel model) {
        if (settings == null) {
            settings = new SettingsModel();
            settings.setDeliveryType(EnumUtil.PriceType.DELIVERY);

        }
    }

    public SettingsModel getSettings() {
        if (settings == null) {
            settings = new SettingsModel();
            settings.setDeliveryType(EnumUtil.PriceType.DELIVERY);

        }
        return settings;
    }

    public boolean isPrinter58mm() {
        return getSettings().isPrinter58mm();
    }

    public void setPrinter58mm(boolean printer58mm) {
        this.settings.setPrinter58mm(printer58mm);
    }

    public boolean isPrinter80mm() {
        return getSettings().isPrinter80mm();
    }

    public void setPrinter80mm(boolean printer80mm) {
        this.settings.setPrinter80mm(printer80mm);
    }

    public static Context getCtx() {
        return ctx;
    }

    public static void setCtx(Context ctx) {
        SettingsManager.ctx = ctx;
    }

    public static String getTAG() {
        return TAG;
    }

    public static void setTAG(String TAG) {
        SettingsManager.TAG = TAG;
    }

    public void setSettings(SettingsModel settings) {
        this.settings = settings;
    }

    public EnumUtil.PriceType getDeliveryType() {
        return getSettings().getDeliveryType();
    }

    public void setDeliveryType(EnumUtil.PriceType type) {
        getSettings().setDeliveryType(type);

    }

    public ArrayList<CompanyModel> getCompany() {
        return getSettings().getCompany();
    }

    public void setCompany(ArrayList<CompanyModel> company) {
        getSettings().setCompany(company);

    }

    public boolean isSpeedloEnabled() {
        return getSettings().isSpeedloEnabled();
    }


    public boolean isCustomDataEnabled() {
        return getSettings().isCustomDataEnabled();
    }


    public UserModel getUser(EnumUtil.SystemType system) {
        switch (system) {
            case ENIGOO:
                return getSettings().getEnigooUser();
            case SPEEDLO:
                return getSettings().getSpeedloUser();
        }
        return null;
    }

    public boolean isAutoPrintEnabled() {
        return getSettings().isAutoPrintEnabled();
    }

    public void setAutoPrint(boolean value) {
        getSettings().setAutoPrintEnabled(value);

    }

    public boolean isPrintRecipeQREnabled() {
        return getSettings().isPrintRecipeQR();
    }

    public void setPrintRecipeQR(boolean value) {
        getSettings().setPrintRecipeQR(value);

    }

    public boolean isPrintKitchenTicketEnabled() {
        return getSettings().isPrintKitchenTicket();
    }

    public void setPrintKitchenTicket(boolean value) {
        getSettings().setPrintKitchenTicket(value);

    }

    public boolean isRecipeLogoEnabled() {
        return getSettings().isPrintRecipeLogo();
    }

    public boolean isPrintKitchenTicket2Times() {
        return getSettings().isPrintKitchenTicket2Times();
    }

    public void setPrintRecipeLogo(boolean value) {
        getSettings().setPrintRecipeLogo(value);

    }

    public boolean isPrintRecipe2Times() {
        return getSettings().isPrintRecipe2Times();
    }

    public void setPrintKitchenTicket2Times(boolean printKitchenTicket2Times) {
        getSettings().setPrintKitchenTicket2Times(printKitchenTicket2Times);

    }

    public void setPrintRecipe2Times(boolean printRecipe2Times) {
        getSettings().setPrintRecipe2Times(printRecipe2Times);

    }


    public boolean isPrintProductCodes() {
        return getSettings().isPrintProductCodes();
    }

    public void setPrintProductCodes(boolean value) {
        getSettings().setPrintProductCodes(value);

    }


    public boolean isDevMode() {
        return getSettings().isDevMode();
    }

    public void setDevMode(boolean value) {
        getSettings().setDevMode(value);

    }

    public boolean isPrintCategories() {
        return getSettings().isPrintCategories();
    }

    public void setPrintCategories(boolean printCategories) {
        getSettings().setPrintCategories(printCategories);

    }

    public boolean isPrintCoverBon() {
        return getSettings().isPrintCoverBon();
    }

    public void setPrintCoverBon(boolean printCoverBon) {
        getSettings().setPrintCoverBon(printCoverBon);

    }

    public Integer getLastUsedSelectorID() {
        return getSettings().getLastUsedSelectorID();
    }

    public void setLastUsedSelectorID(Integer lastUsedSelectorID) {
        getSettings().setLastUsedSelectorID(lastUsedSelectorID);

    }

    public void setPrintOrderOrigin(boolean value){
        getSettings().setPrintOrderOrigin(value);
    }
    public Boolean isPrintOrderOrigin(){
        return getSettings().isPrintOrderOrigin();
    }

    public boolean isAutomaticFinishOrder() {
        return getSettings().isAutomaticFinishOrder();
    }

    public void setAutomaticFinishOrder(boolean automaticFinishOrder) {
        getSettings().setAutomaticFinishOrder(automaticFinishOrder);

    }
    public boolean isInvertBonColors() {
        return getSettings().isInvertBonColors();
    }

    public void setInvertBonColors(boolean invertBonColors) {
        getSettings().setInvertBonColors(invertBonColors);
    }
}

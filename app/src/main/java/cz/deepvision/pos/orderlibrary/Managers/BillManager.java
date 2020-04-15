package cz.deepvision.pos.orderlibrary.Managers;


import cz.deepvision.pos.orderlibrary.models.BillModel;

public class BillManager {
    private static BillManager mBillManger = new BillManager();
    private String currency = "";
    private BillModel currentBill;

    private BillManager() {
    }

    public static BillManager getInstance() {
        return mBillManger;
    }


    public void closeCurrentBill(){
        currentBill=null;
    }
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public BillModel getCurrentBill() {
        return currentBill;
    }

    public void setCurrentBill(BillModel currentBill) {

        this.currentBill = currentBill;
    }

}

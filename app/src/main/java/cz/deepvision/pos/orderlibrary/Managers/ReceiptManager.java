package cz.deepvision.pos.orderlibrary.Managers;

import cz.deepvision.pos.orderlibrary.graphql.type.OrderOriginEnum;
import cz.deepvision.pos.orderlibrary.graphql.type.OrderStateCategoryEnum;
import cz.deepvision.pos.orderlibrary.models.PrintArguments;
import cz.deepvision.pos.orderlibrary.utils.BitmapGeneratingAsyncTask;

public class ReceiptManager {
    private static ReceiptManager mReceiptManager = new ReceiptManager();

    private ReceiptManager() {
    }

    public static ReceiptManager getInstance() {
        return mReceiptManager;
    }

    public void printDoc(boolean kitchen, String orderID, OrderOriginEnum origin, OrderStateCategoryEnum state, BitmapGeneratingAsyncTask.Callback callback) {
        if (kitchen) {
            PrintArguments arguments = new PrintArguments(orderID, true, origin, state);
            printKitchenTicket(callback, arguments);
        }
        if (!kitchen) {
            PrintArguments arguments = new PrintArguments(orderID, false, origin, state);
            printReceipt(callback, arguments);
        }
    }

    public void printReceipt(BitmapGeneratingAsyncTask.Callback callback, PrintArguments arguments) {
        String recipeHTML = HTMLManager.getInstance().generateHTMLForRecipe();
        HTMLManager.getInstance().printBitmap(recipeHTML, 150, callback, arguments);
        BillManager.getInstance().getCurrentBill().setRecipeHTML(recipeHTML);
    }

    public void printKitchenTicket(BitmapGeneratingAsyncTask.Callback callback, PrintArguments arguments) {
        String recipeHTML = HTMLManager.getInstance().generateHTMLForKitchenTicket();
        HTMLManager.getInstance().printBitmap(recipeHTML, 150, callback, arguments);
        BillManager.getInstance().getCurrentBill().setRecipeHTML(recipeHTML);
    }

    public void reprint(String orderID, boolean reprint, BitmapGeneratingAsyncTask.Callback callback) {
        PrintArguments arguments = new PrintArguments(orderID, reprint);

        String recipeHTML = HTMLManager.getInstance().generateHTMLForRecipe();
        HTMLManager.getInstance().printBitmap(recipeHTML, 150, callback, arguments);
        BillManager.getInstance().getCurrentBill().setRecipeHTML(recipeHTML);


        String bonHTML = HTMLManager.getInstance().generateHTMLForKitchenTicket();
        HTMLManager.getInstance().printBitmap(bonHTML, 150, callback, arguments);
        BillManager.getInstance().getCurrentBill().setRecipeHTML(bonHTML);
    }
}

package cz.deepvision.pos.orderlibrary.Managers;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

import cz.deepvision.pos.orderlibrary.graphql.type.OrderOriginEnum;
import cz.deepvision.pos.orderlibrary.graphql.type.OrderStateCategoryEnum;
import cz.deepvision.pos.orderlibrary.models.PrintArguments;
import cz.deepvision.pos.orderlibrary.models.SettingsModel;
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
        generateTicket(callback, arguments, HTMLManager.getInstance().generateHTMLForRecipe());
    }

    public void printKitchenTicket(BitmapGeneratingAsyncTask.Callback callback, PrintArguments arguments) {
        generateTicket(callback, arguments, HTMLManager.getInstance().generateHTMLForKitchenTicket());
    }

    public void reprint(String orderID, boolean reprint, BitmapGeneratingAsyncTask.Callback callback) {
        PrintArguments arguments = getPrintArguments(orderID, reprint, callback);

        if (SettingsManager.getInstance().isPrintKitchenTicketEnabled()) {
            generateTicket(callback, arguments, HTMLManager.getInstance().generateHTMLForKitchenTicket());
        }
    }



    public void reprintAdmin(String orderID, boolean reprint,boolean kitchenPrint, BitmapGeneratingAsyncTask.Callback callback) {
        PrintArguments arguments = getPrintArguments(orderID, reprint, callback);
        if (kitchenPrint) {
            generateTicket(callback, arguments, HTMLManager.getInstance().generateHTMLForKitchenTicket());
        }
    }

    private void generateTicket(BitmapGeneratingAsyncTask.Callback callback, PrintArguments arguments, String s) {
        String bonHTML = s;
        HTMLManager.getInstance().printBitmap(bonHTML, 150, callback, arguments);
        BillManager.getInstance().getCurrentBill().setRecipeHTML(bonHTML);
    }

    private PrintArguments getPrintArguments(String orderID, boolean reprint, BitmapGeneratingAsyncTask.Callback callback) {
        PrintArguments arguments = new PrintArguments(orderID, reprint);

        generateTicket(callback, arguments, HTMLManager.getInstance().generateHTMLForRecipe());
        return arguments;
    }
}

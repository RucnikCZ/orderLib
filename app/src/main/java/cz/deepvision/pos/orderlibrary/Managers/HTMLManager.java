package cz.deepvision.pos.orderlibrary.Managers;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.text.DecimalFormat;

import cz.deepvision.pos.orderlibrary.R;
import cz.deepvision.pos.orderlibrary.models.BillModel;
import cz.deepvision.pos.orderlibrary.models.BranchModel;
import cz.deepvision.pos.orderlibrary.models.DiscountModel;
import cz.deepvision.pos.orderlibrary.models.OrderItem;
import cz.deepvision.pos.orderlibrary.models.PrintArguments;
import cz.deepvision.pos.orderlibrary.models.UserModel;
import cz.deepvision.pos.orderlibrary.models.VatModel;
import cz.deepvision.pos.orderlibrary.utils.BitmapGeneratingAsyncTask;
import cz.deepvision.pos.orderlibrary.utils.BitmapUtil;
import cz.deepvision.pos.orderlibrary.utils.EnumUtil;

public class HTMLManager {
    private static HTMLManager mHTMLManager = new HTMLManager();
    private BitmapGeneratingAsyncTask generator;

    private StringBuilder mHTML = new StringBuilder();

    private String currentHtml = "";
    private int currentZoom = 0;

    private HTMLManager() {
    }

    public static HTMLManager getInstance() {
        return mHTMLManager;
    }

    public void printBitmap(String html, int zoom, BitmapGeneratingAsyncTask.Callback callback, PrintArguments arguments) {
        BillModel model = BillManager.getInstance().getCurrentBill();
        generator = new BitmapGeneratingAsyncTask(SettingsManager.getCtx(), html, 580, callback, zoom, arguments, model);
        if (arguments.isReprint()) {
            if (SettingsManager.getInstance().isPrinter58mm() && !SettingsManager.getInstance().isPrinter80mm()) {
                BitmapGeneratingAsyncTask generator58 = new BitmapGeneratingAsyncTask(SettingsManager.getCtx(), html, 384, callback, zoom, arguments, model);
                generator58.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
            } else {
                generator.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
            }
        } else {
            // Tisk 58
            if (SettingsManager.getInstance().isPrinter58mm() && !SettingsManager.getInstance().isPrinter80mm()) {
                BitmapGeneratingAsyncTask generator58 = new BitmapGeneratingAsyncTask(SettingsManager.getCtx(), html, 384, callback, zoom, arguments, model);
                generator58.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
            }
            // Tisk 58 a 80
            else if (SettingsManager.getInstance().isPrinter58mm() && SettingsManager.getInstance().isPrinter80mm()) {
                BitmapGeneratingAsyncTask generator58 = new BitmapGeneratingAsyncTask(SettingsManager.getCtx(), html, 384, callback, zoom, arguments, model);
                generator58.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);

                BitmapGeneratingAsyncTask generator80 = new BitmapGeneratingAsyncTask(SettingsManager.getCtx(), html, 580, callback, zoom, arguments, model);
                generator80.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
            } else {
                // Tisk 80
                generator.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
            }
        }
    }

    public String generateHTMLForRecipe() {
        this.mHTML = new StringBuilder();
        addCompanyHeader();
        addRecipeHeader();
        addOrderItems();
        addPayment();
        if (SettingsManager.getInstance().isSpeedloEnabled()) {
            addDeliveryInfo();
            addEET();
            if (SettingsManager.getInstance().isPrintRecipeQREnabled()) {
                addOrderQR();
            }
        }

        addFooter();

        return this.mHTML.toString();
    }

    public String generateHTMLForKitchenTicket() {
        this.mHTML = new StringBuilder();
        Context ctx = SettingsManager.getCtx();
        BillModel currentBill = BillManager.getInstance().getCurrentBill();
        if (!currentBill.getRecyclereceiptID().equals("")) {
            String sector = currentBill.getSectorPlace();
            String recycledID = currentBill.getRecyclereceiptID();
            this.mHTML.append("</br></br></br></br>");
            this.mHTML.append("<div style='text-align: center;color: black; background-color: white;font-size: 35px;'>");
            this.mHTML.append("<strong>").append(recycledID).append("</strong>").append("<br/>");
            this.mHTML.append("<strong>").append(sector).append("</strong>");
            this.mHTML.append("</div>");
        } else {
            this.mHTML.append("<div style='height:160px;'></div>");
        }

        this.mHTML.append("<div style='text-align: center;border: 2px solid black;'>" +
                "<div style='color: white; background-color: black; text-nalign: center;'>"
                + ctx.getString(R.string.create_order_order) + " ").append(currentBill.getId()).append("</div>").append("<table style='width: 100%;font-size: 25px;'>").append("<tbody>");

        for (String[] item : currentBill.getSimpleBillItems()) {
            if (item.length == 1) {
                this.mHTML.append("<tr><td colspan='2' style='text-align: center;font-weight:bold;font-size:20px'>" + item[0] + "</td></tr>");
            } else {
                if (item.length == 3) {
                    String itemString = "<tr>" +
                            "<td style='text-align: left;font-weight:bold;white-space:nowrap'>" + item[0] + "</td>" +
                            "<td style='text-align: left;font-weight:bold'>" + item[1] + "</td>" +
                            "</tr>";
                    this.mHTML.append(itemString);
                } else {
                    String itemString = "<tr>" +
                            "<td style='text-align: left;white-space:nowrap'>" + item[0] + "</td>" +
                            "<td style='text-align: left;'>" + item[1] + "</td>" +
                            "</tr>";
                    this.mHTML.append(itemString);
                }
            }
        }

        this.mHTML.append("</tbody>" +
                "</table>" +
                "</div>");
        this.mHTML.append("<div style='text-align: center; border-style: solid;'>");
        this.mHTML.append("<div style='color: white; background-color: black; text-align: center;'>" + "Objednáno v: ").append(currentBill.getDate()).append("</div>");
        String deliveryTypeText = currentBill.getDeliveryType().equals(EnumUtil.DeliveryType.MESSENGER) ? ctx.getString(R.string.html_delivery) : ctx.getString(R.string.html_personal_pick);
        this.mHTML.append("<div style='color: black;border:2px solid black;padding:5px;font-size:20px;font-weight:bold; background-color: white; text-align: center;'>" + "Typ doručení: ").append(deliveryTypeText).append("</div>");


        String backColor = "white";
        String backColor2 = "black";
        if (SettingsManager.getInstance().isInvertBonColors()) {
            backColor = "black";
            backColor2 = "white";
        }
        if (!currentBill.getNote().equals("")) {
            this.mHTML.append("<div style='background-color:" + backColor2 + ";color:" + backColor + ";text-align: center;'>" + "Poznámka ").append("</div>").append("<table style='width: 100%;background-color:" + backColor + ";color:" + backColor2 + ";'>").append("<tbody>").append("<tr>");
            this.mHTML.append("<td style='text-align: left;font-size: 22px;'>").append(currentBill.getNote()).append("</td>").append("</tr>");
            this.mHTML.append("</tbody></table>");
        }
        this.mHTML.append("</div");
        return this.mHTML.toString();
    }


    private void addCompanyHeader() {
        BillModel bill = BillManager.getInstance().getCurrentBill();
        BranchModel branch = bill.getBranchModel();
        Context ctx = SettingsManager.getCtx();

        if (SettingsManager.getInstance().isRecipeLogoEnabled()) {
            if (branch.getLogoURL() != null && !branch.getLogoURL().equals("") && !branch.getLogoURL().isEmpty()) {
                this.mHTML.append("<div style='text-align: center;'><table style='width:100%;'+q><tr><td style='text-align:center;'><img width='75%;' src='").append(branch.getLogoURL()).append("'></img></td></table></div>");
            }
        }


        if (!bill.getRecyclereceiptID().equals("")) {
            String sector = bill.getSectorPlace();
            String recycledID = bill.getRecyclereceiptID();
            this.mHTML.append("<div style='height:200px; text-align: center;color: black; background-color: white;font-size: 35px;'>");
            this.mHTML.append("<strong>").append(recycledID).append("</strong>").append("<br/>");
            this.mHTML.append("<strong>").append(sector).append("</strong>");
            this.mHTML.append("</div>");
        }

        this.mHTML.append("<div style='text-align: center;'>" +
                "<div style='color: black; background-color: white; text-align: center;'>" +
                branch.getBrandName() +
                "<br/>" + branch.getCompanyName() +
                "</div>" +
                branch.getCompanyAdress() + "<br/>" +
                ctx.getString(R.string.companyID) + ":" + branch.getIdentificator() + "<br/>" +
                ctx.getString(R.string.companyVatID) + ":" + branch.getVatIdentificator() + "<br/>" +
                "</div>" +
                "<br/>");
    }

    private void addRecipeHeader() {
        BillModel bill = BillManager.getInstance().getCurrentBill();
        BranchModel branch = bill.getBranchModel();
        UserModel user = new UserModel();

        if (SettingsManager.getInstance().isSpeedloEnabled()) {
            user = SettingsManager.getInstance().getUser(EnumUtil.SystemType.SPEEDLO);
        }
        Context ctx = SettingsManager.getCtx();
        this.mHTML.append("<div style='text-align: center;border: 2px solid black;'>" +
                "<div style='color: white; background-color: black; text-align: center;'>" +
                ctx.getString(R.string.create_order_ticket_number) + " " + bill.getId() +
                "</div>" +
                "<table style='width: 100%;'>" +
                "<tbody>" +
                "<tr>" +
                "<td style='text-align: left;'>" + ctx.getString(R.string.create_order_ordered_in) + "</td>" +
                "<td style='text-align: right;'>" + bill.getDate() + "</td>" +
                "</tr>");

        if (!bill.getDeliverAt().equals("")) {
            this.mHTML.append("<tr>" +
                    "<td style='text-align: left;font-weight:bold;'>" + ctx.getString(R.string.deliverAt) + "</td>" +
                    "<td style='text-align: right;font-weight:bold;'>" + bill.getDeliverAt() + "</td>" +
                    "</tr>");
        }
        this.mHTML.append("<tr>" +
                "<td style='text-align: left;'>" + ctx.getString(R.string.create_order_service) + "</td>" +
                "<td style='text-align: right;'>" + user.getFirstName() + " " + user.getLastName() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='text-align: left;'>" + ctx.getString(R.string.create_order_run_cashier) + "</td>" +
                "<td style='text-align: right;'>" + branch.getEstablishmentID() + "," + branch.getPosID() + "</td></tr>");
        if (SettingsManager.getInstance().isPrintOrderOrigin()) {
            mHTML.append("<td style='text-align: lef;'>Původ</td>");
            mHTML.append("<td style='text-align: right;'>" + bill.getOrderOrigin() + "</td></tr>");
        }

        this.mHTML.append(
                "</tbody>" +
                        "</table>" +
                        "</div>" +
                        "<br/>");
    }

    private void addOrderItems() {
        Context ctx = SettingsManager.getCtx();
        BillModel currentBill = BillManager.getInstance().getCurrentBill();

        this.mHTML.append("<div style='text-align: center;border: 2px solid black;'>" +
                "<div style='color: white; background-color: black; text-align: center;'>" +
                ctx.getString(R.string.create_order_order) + " " + currentBill.getId() +
                "</div>" +
                "<table style='width: 100%;'>" +
                "<tbody>");

        for (String[] item : currentBill.getCartPrintList()) {
            if (item.length == 1) {
                String itemString = "<tr>" +
                        "<td colspan='4' style='text-align: center;font-weight:bold;font-size:20px'>" + item[0] + "</td>" +
                        "</tr>";
                this.mHTML.append(itemString);
            } else {
                if (item[2].equals("0.0 " + BillManager.getInstance().getCurrency())) {
                    item[2] = "zdarma";
                }
                String itemString = "<tr>" +
                        "<td style='text-align: left;'>" + item[0] + "</td>" +
                        "<td style='text-align: left;'></td>" +
                        "<td style='text-align: left;'>" + item[1] + "</td>" +
                        "<td style='text-align: right;white-space:nowrap;'>" + item[2] + "</td>" +
                        "</tr>";
                this.mHTML.append(itemString);
            }

        }
        for (DiscountModel discountModel : currentBill.getDiscounts()) {
            this.mHTML.append("<tr>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'>" + discountModel.getDescription() + "</td>" +
                    "<td style='text-align: right;white-space:nowrap;'>" + formatDouble(discountModel.getValue()) + BillManager.getInstance().getCurrency() + " </td>" +
                    "</tr>");
        }
        if (currentBill.getTransport() != null) {
            this.mHTML.append("<tr>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'>" + currentBill.getTransport().getName() + "</td>" +
                    "<td style='text-align: right;white-space:nowrap;'>" + formatDouble(currentBill.getTransport().getPrice()) + BillManager.getInstance().getCurrency() + " </td>" +
                    "</tr>");
        }
        if (currentBill.getTip() != null) {
            this.mHTML.append("<tr>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'>" + currentBill.getTip().getName() + "</td>" +
                    "<td style='text-align: right;white-space:nowrap;'>" + formatDouble((currentBill.getTip().getPrice())) + " " + BillManager.getInstance().getCurrency() + " </td>" +
                    "</tr>");
        }
        if (currentBill.getExtraCharge() != null) {
            this.mHTML.append("<tr>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'>" + currentBill.getExtraCharge().getName() + "</td>" +
                    "<td style='text-align: right;white-space:nowrap;'>" + formatDouble((currentBill.getExtraCharge().getPrice())) + " " + BillManager.getInstance().getCurrency() + " </td>" +
                    "</tr>");
        }
        if (SettingsManager.getInstance().isPrintProductCount()) {
            int size = 0;
            for (OrderItem item : currentBill.getItems()) {
                size += item.getCount();
            }
            this.mHTML.append("<tr>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'></td>" +
                    "<td style='text-align: left;'>" + "Celkový počet položek v objednávce" + "</td>" +
                    "<td style='text-align: right;white-space:nowrap;'>" + size + " </td>" +
                    "</tr>");
        }
        this.mHTML.append("</tbody>" +
                "</table>" +
                "</div>" +
                "<br/>");
    }

    private void addPayment() {
        BillModel currentBill = BillManager.getInstance().getCurrentBill();
        Context ctx = SettingsManager.getCtx();
        String billPrice = currentBill.getPrice();
        if (!currentBill.getPrice().isEmpty()) {
            billPrice = currentBill.getPrice();
        }
        this.mHTML.append("<div style='text-align: left; border_left-style: solid;border: 2px solid black;'>" +
                "<div style='color: white; background-color: black; text-align: center;'>" +
                ctx.getString(R.string.create_order_payment) +
                "<strong>" + (currentBill.getPayment().equals(EnumUtil.PaymentType.CASH) ? ctx.getString(R.string.html_cash) : ctx.getString(R.string.html_card) + " | ") + (currentBill.getPayment().equals(EnumUtil.PaymentType.CARD) ? currentBill.getPaymentStatus() : "") + "</strong>" +
                "</div>" +
                "<table style='width: 100%;'>" +
                "<tbody>" +
                "<tr>" +
                "<td style='text-align: left;'><Strong>" + ctx.getString(R.string.sum) +
                "</Strong></td>" +
                "<td style='text-align: left;'></td>" +
                "<td style='text-align: left;'></td>" +
                "<td style='text-align: right;'><Strong>" + billPrice + " " + BillManager.getInstance().getCurrency() + "</Strong></td>" +
                "</tr>" +
                "<tr style='background-color:black;color:white;font-size: 11px'>" +
                "<td style='text-align: left;'>" + ctx.getString(R.string.vat) + "</td>" +
                "<td style='text-align: left;'>" + ctx.getString(R.string.without_vat) + "</td>" +
                "<td style='text-align: left;'>" + ctx.getString(R.string.vat) + "</td>" +
                "<td style='text-align: right;'>" + ctx.getString(R.string.sum) + "</td>" +
                "</tr>");
        currentBill.getVatPricing();

        DecimalFormat dec = new DecimalFormat("#.00");
        VatModel sum = new VatModel();
        for (VatModel vatPrice : currentBill.getVatPrices()) {
            sum.setBaseDPH(sum.getBaseDPH() + vatPrice.getBaseDPH());
            sum.setDphRest(sum.getDphRest() + vatPrice.getDphRest());
            sum.setSum(sum.getSum() + vatPrice.getSum());
        }
        for (VatModel vatPrice : currentBill.getVatPrices()) {
            Object pref = vatPrice.getVatPricing() + "%";
            this.mHTML.append("<tr style='font-size: 11px'>" +
                    "<td style='text-align: left;'>" + pref + "</td>" +
                    "<td style='text-align: left;'>" + formatDouble(vatPrice.getBaseDPH()) + "</td>" +
                    "<td style='text-align: left;'>" + formatDouble(vatPrice.getDphRest()) + "</td>" +
                    "<td style='text-align: right;'>" + formatDouble(vatPrice.getSum()) + "</td>" +
                    "</tr>");
        }
//        this.mHTML.append("<tr style='font-size: 11px'>" +
//                "<td style='text-align: left;'>" + ctx.getString(R.string.sum) + "</td>" +
//                "<td style='text-align: left;'>" + formatDouble(sum.getBaseDPH()) + "</td>" +
//                "<td style='text-align: left;'>" + formatDouble(sum.getDphRest()) + "</td>" +
//                "<td style='text-align: right;'>" + formatDouble(sum.getSum()) + "</td>" +
//                "</tr>");

        this.mHTML.append("</tbody>" +
                "</table>" +
                "</div>" +
                "<br/>");

    }

    private void addDeliveryInfo() {
        Context ctx = SettingsManager.getCtx();

        BillModel bill = BillManager.getInstance().getCurrentBill();
        String deliveryTypeText = bill.getDeliveryType().equals(EnumUtil.DeliveryType.MESSENGER) ? ctx.getString(R.string.html_delivery) : ctx.getString(R.string.html_personal_pick);

        this.mHTML.append("<div style='text-align: left;border: 2px solid black;'>" +
                "<div style='color: white; background-color: black; text-align: center;'>" +
                ctx.getString(R.string.delivery_type) + "<strong>" + deliveryTypeText + "</strong>" +
                "</div><span style='font-size: 25px'>" +
                bill.getName() + "<br/>" +
                bill.getAddress() + "<br/>" +
                "<strong>" + bill.getPhone() + "</strong><br />" +
                (!(bill.getNote().equals("")) ? bill.getNote() : "") +
                "</span>" +
                "</div>");

    }

    private void addEET() {
        BillModel bill = BillManager.getInstance().getCurrentBill();
        if (bill.getReceiptEetString() != null) {
            if (!bill.getReceiptEetString().isEmpty()) {
                String eet = bill.getReceiptEetString();
                this.mHTML.append("<div style='text-align: left;text-align: center;word-wrap: break-word;'>" +
                        "<div style='color: white; background-color: black; text-align: center;'>" +
                        "EET" +
                        "</div>" +
                        eet +
                        "</div>");
            }
        }
    }

    private void addOrderQR() {
        if (SettingsManager.getInstance().isPrintRecipeQREnabled()) {

            BillModel bill = BillManager.getInstance().getCurrentBill();
            if (bill.getDeliveryType().equals(EnumUtil.DeliveryType.PICK_UP)) {
                return;
            }

            Bitmap qrBitmap = BitmapUtil.generateBitmap(bill.getId(), 9, 300, 300);
            String qrPath = BitmapUtil.bitmapToBase64(qrBitmap);
            qrBitmap.recycle();

            this.mHTML.append("<div style='text-align: center;'><table style='width:100%;'><tr><td><img src='data:image/png;base64, " + qrPath + "'></img></td><td>QR kod pro rozvoz</td></table></div>");
        }
    }

    private void addFooter() {
        Context ctx = SettingsManager.getCtx();
        String system = "T1000";
        if (SettingsManager.getInstance().isSpeedloEnabled()) {
            system = "SPEEDLO";
        }
        this.mHTML.append("<br/><br/>");
        this.mHTML.append("<div style='text-align: center;'>" + ctx.getString(R.string.app_name) + "<br/>" + ctx.getString(R.string.create_order_thank_you) + "<br/> Powered by " + system + " with \uD83D\uDDA4</div>");
    }

    private String formatDouble(Double value) {
        DecimalFormat dec = new DecimalFormat("#0.00");
        return dec.format(value).replace(",", ".");
    }


}

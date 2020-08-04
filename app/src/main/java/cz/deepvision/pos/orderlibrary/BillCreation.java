package cz.deepvision.pos.orderlibrary;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.deepvision.pos.orderlibrary.Managers.BillManager;
import cz.deepvision.pos.orderlibrary.Managers.HTMLManager;
import cz.deepvision.pos.orderlibrary.Managers.ReceiptManager;
import cz.deepvision.pos.orderlibrary.Managers.SettingsManager;
import cz.deepvision.pos.orderlibrary.graphql.fragment.GenericOrder;
import cz.deepvision.pos.orderlibrary.graphql.type.DeliveryTypeEnum;
import cz.deepvision.pos.orderlibrary.graphql.type.PaymentTypeEnum;
import cz.deepvision.pos.orderlibrary.graphql.type.WarePriceTypeEnum;
import cz.deepvision.pos.orderlibrary.models.BillModel;
import cz.deepvision.pos.orderlibrary.models.CompanyModel;
import cz.deepvision.pos.orderlibrary.models.DiscountModel;
import cz.deepvision.pos.orderlibrary.models.OrderItem;
import cz.deepvision.pos.orderlibrary.models.PrintArguments;
import cz.deepvision.pos.orderlibrary.models.SettingsModel;
import cz.deepvision.pos.orderlibrary.models.VatModel;
import cz.deepvision.pos.orderlibrary.utils.BitmapGeneratingAsyncTask;
import cz.deepvision.pos.orderlibrary.utils.EnumUtil;
import cz.deepvision.pos.orderlibrary.utils.EnumUtil.PaymentStatus;
import cz.deepvision.pos.orderlibrary.utils.TimeUtil;

import static cz.deepvision.pos.orderlibrary.utils.EnumUtil.PaymentStatus.*;
import static cz.deepvision.pos.orderlibrary.utils.EnumUtil.PaymentType.*;

/**
 * <li>Firstly use method setPrintSetings, input param is current settings on device</li>
 * <li>Then use which method u need ... reprint, cancel or print</li>
 */

public class BillCreation {
    public static BillCreation billcreator = new BillCreation();
    private static volatile boolean isReprint;
    private static volatile boolean isStorno;
    private static volatile boolean isPosOrder;
    private CompanyModel companyModel;
    private static String BASE_TAG;
    private BitmapGeneratingAsyncTask.Callback callback;


    public static BillCreation getInstance() {
        isReprint = false;
        isStorno = false;
        return billcreator;
    }

    public void setPrintSettings(SettingsModel model, final String TAG, Context ctx, BitmapGeneratingAsyncTask.Callback callback, CompanyModel company) {
        SettingsManager.getInstance().setSettings(model);
        BASE_TAG = TAG;
        SettingsManager.setTAG(TAG);
        SettingsManager.setCtx(ctx);
        this.companyModel = company;
        this.callback = callback;
    }

    public void reprintOrder(GenericOrder order) {
        isReprint = true;
        isStorno = false;
        isPosOrder = false;
        createOrderFromSpeedlo(order, false);
    }

    public void cancelOrder(GenericOrder order) {
        isReprint = false;
        isStorno = true;
        isPosOrder = true;
        createOrderFromSpeedlo(order, isPosOrder);
    }

    private Double getCorrectPriceOrderItem(GenericOrder.Item item) {
        double vat;
        if (item.recipe().prices().get(0).warePriceType().enum_().equals(WarePriceTypeEnum.DELIVERY)) {
            vat = item.recipe().prices().get(0).vat().vat();
        } else {
            vat = item.recipe().prices().get(1).vat().vat();
        }
        return vat;
    }

    private Double getCorrectPriceSideDish(GenericOrder.SideDish sideDish) {
        double vat;
        if (sideDish.recipe().prices().get(0).warePriceType().enum_().equals(WarePriceTypeEnum.DELIVERY)) {
            vat = sideDish.recipe().prices().get(0).vat().vat();
        } else {
            vat = sideDish.recipe().prices().get(1).vat().vat();
        }
        return vat;
    }

    private Double getCorrectetPriceCover(GenericOrder.Cover cover) {
        double vat;
        if (cover.recipe().prices().get(0).warePriceType().enum_().equals(WarePriceTypeEnum.DELIVERY)) {
            vat = cover.recipe().prices().get(0).vat().vat();
        } else {
            vat = cover.recipe().prices().get(1).vat().vat();
        }
        return vat;
    }

    public void createOrderFromSpeedlo(GenericOrder order, boolean pos) {
        // Vytvoření účtu, nalití všech produktů

        isPosOrder = pos;
        BillModel bill = new BillModel();
        BillManager.getInstance().setCurrentBill(bill);
        for (GenericOrder.Item product : order.items()) {
            bill.addCategory(product.recipe().wareCategories().get(0).nameLabel());
            VatModel vat = new VatModel(getCorrectPriceOrderItem(product));
            OrderItem orderItem = new OrderItem(1, product.recipe().nameLabel(), product.price().value(), vat.getVatPricing(), product.recipe().code(), product.recipe().wareCategories().get(0).nameLabel());
            if (product.note() != null)
                orderItem.setNote(product.note());
            bill.getVatPrices().add(vat);

            for (GenericOrder.SideDish sideDish : product.sideDishes()) {
                vat = new VatModel(getCorrectPriceSideDish(sideDish));
                OrderItem extra = new OrderItem(1, sideDish.recipe().nameLabel(), sideDish.price().value(), vat.getVatPricing(), sideDish.recipe().code(), "");
                bill.addSideDish(orderItem, extra);
                bill.getVatPrices().add(vat);

            }
            List<OrderItem> covers = new ArrayList<>();
            for (GenericOrder.Cover cover : product.covers()) {
                vat = new VatModel(getCorrectetPriceCover(cover));
                OrderItem wrap = new OrderItem(1, "Obal", cover.price().value(), vat.getVatPricing());
                covers.add(wrap);
            }
            orderItem.setCover(covers);
            bill.getVatPrices().add(vat);
            bill.addItem(orderItem);
        }

        // Nastavení společnosti, id a celkové ceny
        BillManager.getInstance().setCurrency(order.companyBranch().defaultCurrency().code());
        bill.setCompanyModel(companyModel);
        bill.setId(order.id());
        bill.setPrice(String.valueOf(order.totalSum().roundedValue()));


        // Vytvoření objednávky a info o doručení
        //TODO: upravit obecně
        if (order.deliverAt() != null) {
            bill.setDeliverAt(TimeUtil.formatDateEurope(order.deliverAt()));
        }
        if (order.acceptedAt() != null) {
            bill.setDate(TimeUtil.formatDateEurope(order.acceptedAt()));
        }

        // Nastavení typu doručení a jeho ceny
        if (order.deliveryType() != null) {
            bill.setDeliveryType(order.deliveryType().enum_().equals(DeliveryTypeEnum.PICKUP) ? EnumUtil.DeliveryType.PICK_UP : EnumUtil.DeliveryType.MESSENGER);
            if (order.warePriceType().enum_().equals(WarePriceTypeEnum.DELIVERY)) {
                if (order.transportFee().value() != 0) {
                    VatModel vat = new VatModel(21.0);
                    OrderItem delivery = new OrderItem(1, "Doprava", order.transportFee().value(), vat.getVatPricing());
                    BillManager.getInstance().getCurrentBill().setTransport(delivery);
                    bill.getVatPrices().add(vat);
                }
            }
        }
        // Připsání dýška k objednávce
        if (order.tip().value() != 0.0) {
            VatModel vat = new VatModel(21.0);
            bill.getVatPrices().add(vat);
            OrderItem tip = new OrderItem(1, SettingsManager.getCtx().getString(R.string.order_tip), order.tip().value(), vat.getVatPricing());
            BillManager.getInstance().getCurrentBill().setTip(tip);
        }
        if (order.extraCharge().value() != 0.0) {
            VatModel vat = new VatModel(21.0);
            bill.getVatPrices().add(vat);
            OrderItem extraCharge = new OrderItem(1, SettingsManager.getCtx().getString(R.string.extra_charge), order.extraCharge().value(), vat.getVatPricing());
            BillManager.getInstance().getCurrentBill().setExtraCharge(extraCharge);
        }
        // Nastavení adresy a informací o klientovi
        bill.setAddress(order.address().street() + " " + order.address().houseNumber() + ", " + order.address().city());

        if (order.address().original() != null) {
            if (!order.address().original().equals(""))
                bill.setAddress(order.address().original());
        }

        if (order.customer().defaultPhone() != null) {
            bill.setPhone(Objects.requireNonNull(order.customer().defaultPhone()).phone());
        }

        if (order.customer().firstName() != null || order.customer().lastName() != null) {
            bill.setName(order.customer().firstName() + " " + order.customer().lastName());
        }
        if (order.customer().defaultEmail() != null) {
            bill.setEmail(Objects.requireNonNull(order.customer().defaultEmail()).email());
        }

        // Nastavení typu platby
        PaymentTypeEnum speedloPayment = order.orderPayments().get(0).paymentMethod().paymentType().enum_();
        if (speedloPayment.equals(PaymentTypeEnum.CASH)) {
            bill.setPayment(CASH);
        } else {
            bill.setPayment(CARD);
        }
        bill.setPaymentStatus(order.orderStates().contains(PAID) ? PAID : UNPAID);


        // Wi-fi  recycle ID
        if (order.invoiceSequenceResettable() != null)
            bill.setRecyclereceiptID(String.valueOf(order.invoiceSequenceResettable()));
        if (order.sector() != null) {
            if (!order.sector().name().equals(""))
                bill.setSectorPlace(order.sector().name());
        }

        // Slevys
        for (GenericOrder.Discount discount : order.discounts()) {
            DiscountModel discountModel = new DiscountModel();
            discountModel.setDescription(discount.description());
            discountModel.setValue(discount.price().value());
            bill.getDiscounts().add(discountModel);
        }

        // Nastavení EET a poznámky
        if (order.note() != null) {
            bill.setNote(order.note());
        }

        if (order.eet() != null) {
            String eetString = order.eet().mode() + "\n";
            if (!order.eet().fik().isEmpty()) {
                eetString += "FIK: " + order.eet().fik() + "\n";
                eetString += "BKP: " + order.eet().bkp() + "\n";
            } else {
                eetString += "PKP: " + order.eet().pkp() + "\n";
                eetString += "BKP: " + order.eet().bkp() + "\n";
            }
            bill.setReceiptEetString(eetString);
        }

        boolean isReceived = false;
        boolean isPaid = false;
        boolean isAccepted = false;
        boolean isInProgress = false;
        boolean isFinished = false;
        boolean isCanceled = false;
        boolean isKitchenPrinted = false;
        boolean isTicketPrinted = false;
        for (GenericOrder.OrderState state : order.orderStates()) {

            switch (state.enum_()) {
                case PAID:
                    isPaid = true;
                    break;
                case RECEIVED:
                    isReceived = true;
                    break;
                case ACCEPTED:
                    isAccepted = true;
                    break;
                case IN_PROGRESS:
                    isInProgress = true;
                    break;
                case FINISHED:
                    isFinished = true;
                    break;
                case CANCELED:
                    isCanceled = true;
                    break;
                case BILL_PRINTED:
                    isTicketPrinted = true;
                    break;
                case KITCHEN_TICKET_PRINTED:
                    isKitchenPrinted = true;
                    break;
            }
        }


        if (isReceived && isPaid) {
            bill.setType(EnumUtil.BillType.INCOMING);
            bill.setStatus(EnumUtil.OrderStatus.NEW);
        }

        if (isAccepted && isInProgress) {
            bill.setType(EnumUtil.BillType.OPENED);
            bill.setStatus(EnumUtil.OrderStatus.RECEIVED);
        }

        if (isFinished) {
            bill.setType(EnumUtil.BillType.CLOSED);
            bill.setStatus(EnumUtil.OrderStatus.COMPLETE);
        }

        if (isCanceled) {
            bill.setType(EnumUtil.BillType.CLOSED);
            bill.setStatus(EnumUtil.OrderStatus.CANCELED);
        }
        boolean isAutoPrint = SettingsManager.getInstance().isAutoPrintEnabled();
        if (isReprint) {
            reprintOrder(order, bill);
        } else if (isStorno) {
            bill.setStorno(true);
            HTMLManager.getInstance().printBitmap("", 150, callback, new PrintArguments());
        } else {
            printOrder(order, bill, isAccepted, isInProgress, isKitchenPrinted, isTicketPrinted, isAutoPrint);
        }
    }

    private void printOrder(GenericOrder order, BillModel bill, boolean isAccepted, boolean isInProgress, boolean isKitchenPrinted, boolean isTicketPrinted, boolean isAutoPrint) {
        Log.d(BASE_TAG, order.id() + " is ready for print from server");
        if (isPosOrder) {
            if (isAccepted && isInProgress && isAutoPrint && (!isKitchenPrinted || !isTicketPrinted)) {
                // FiskalProUtil.print(T1000Activity.usbService, false);
                if (!BillManager.getInstance().getCurrentBill().isHasReceipt()) {
                    Log.d(BASE_TAG, "Printing order:" + bill.getId());
                    bill.setHasReceipt(true);
                    if (SettingsManager.getInstance().isPrintKitchenTicketEnabled()) {
                        printKitchenTicket(order, isKitchenPrinted);
                    }
                    printReceipt(order, isTicketPrinted);
                }
            }
        } else {
            if (!isKitchenPrinted || !isTicketPrinted) {
                printKitchenTicket(order, isKitchenPrinted);
                printReceipt(order, isTicketPrinted);
            }
        }
    }

    private void printReceipt(GenericOrder order, boolean isTicketPrinted) {
        if (!isTicketPrinted) {
            ReceiptManager.getInstance().printDoc(false, order.id(), order.orderOrigin().enum_(), order.orderStateCategory(), callback);
        }
    }

    private void printKitchenTicket(GenericOrder order, boolean isKitchenPrinted) {
        if (!isKitchenPrinted) {
            ReceiptManager.getInstance().printDoc(true, order.id(), order.orderOrigin().enum_(), order.orderStateCategory(), callback);
        }
    }

    private void reprintOrder(GenericOrder order, BillModel bill) {
        Log.d(BASE_TAG, "Reprinting order: " + bill.getId());
        ReceiptManager.getInstance().reprint(order.id(), isReprint, callback);

    }
}
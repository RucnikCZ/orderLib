package cz.deepvision.pos.orderlibrary.models;

import android.content.Context;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cz.deepvision.pos.orderlibrary.Managers.BillManager;
import cz.deepvision.pos.orderlibrary.Managers.SettingsManager;
import cz.deepvision.pos.orderlibrary.utils.EnumUtil;


public class BillModel {
    private String date = "";
    private String deliverAt = "";
    private String address = "";
    private String name = "Guest";
    private String price = "";
    private String email = "";
    private EnumUtil.PriceType delivery = SettingsManager.getInstance().getDeliveryType();
    private EnumUtil.PaymentType payment = EnumUtil.PaymentType.CASH;
    private EnumUtil.OrderStatus status = EnumUtil.OrderStatus.NEW;
    private EnumUtil.DeliveryType deliveryType = EnumUtil.DeliveryType.PICK_UP;
    private String source = "";
    private String id = "";
    private String paymentStatus = "";
    private String phone = "";
    private EnumUtil.BillType type = EnumUtil.BillType.CURRENT;
    private String note = "";
    private OrderItem transport = null;


    private boolean hasReceipt = false;
    private String receiptID = "";
    private String recyclereceiptID = "";
    private OrderItem tip = null;
    private OrderItem extraCharge = null;
    private String sectorPlace = "";
    private String orderOrigin = "";
    private String receiptDate = "";
    private String receiptCashID = "";
    private String receiptCashierID = "";
    private String receiptEstablishmentID = "";
    private String receiptEetString = "";
    private double receiptPaid = 0.00;
    private double receiptReturned = 0.00;
    private boolean isRemote = false;
    private String recipeHTML = "";
    private boolean createOrderResponseSucces = false;
    private List<String> categories = new ArrayList<>();
    private List<OrderItem> items = new ArrayList<>();
    private BranchModel branchModel;
    private boolean isStorno = false;


    private HashSet<VatModel> vatPrices = new HashSet<>();

    private List<DiscountModel> discounts = new ArrayList<>();

    public OrderItem getTransport() {
        return transport;
    }

    public void setTransport(OrderItem transport) {
        this.transport = transport;
    }

    public void setVatPrices(HashSet<VatModel> vatPrices) {
        this.vatPrices = vatPrices;
    }

    public void setDiscounts(List<DiscountModel> discounts) {
        this.discounts = discounts;
    }

    public BillModel() {
        this.date = "";
    }

    public EnumUtil.PriceType getDelivery() {
        return delivery;
    }

    public void setDelivery(EnumUtil.PriceType delivery) {
        this.delivery = delivery;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getOrderOrigin() {
        return orderOrigin;
    }

    public void setOrderOrigin(String orderOrigin) {
        this.orderOrigin = orderOrigin;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptCashID() {
        return receiptCashID;
    }

    public void setReceiptCashID(String receiptCashID) {
        this.receiptCashID = receiptCashID;
    }

    public String getReceiptCashierID() {
        return receiptCashierID;
    }

    public void setReceiptCashierID(String receiptCashierID) {
        this.receiptCashierID = receiptCashierID;
    }

    public String getReceiptEstablishmentID() {
        return receiptEstablishmentID;
    }

    public void setReceiptEstablishmentID(String receiptEstablishmentID) {
        this.receiptEstablishmentID = receiptEstablishmentID;
    }

    public double getReceiptPaid() {
        return receiptPaid;
    }

    public void setReceiptPaid(double receiptPaid) {
        this.receiptPaid = receiptPaid;
    }

    public double getReceiptReturned() {
        return receiptReturned;
    }

    public void setReceiptReturned(double receiptReturned) {
        this.receiptReturned = receiptReturned;
    }

    public String getRecipeHTML() {
        return recipeHTML;
    }

    public boolean isCreateOrderResponseSucces() {
        return createOrderResponseSucces;
    }

    public void setCreateOrderResponseSucces(boolean createOrderResponseSucces) {
        this.createOrderResponseSucces = createOrderResponseSucces;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public EnumUtil.PaymentType getPayment() {
        return payment;
    }

    public void setPayment(EnumUtil.PaymentType payment) {
        this.payment = payment;
    }

    public EnumUtil.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumUtil.OrderStatus status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EnumUtil.BillType getType() {
        return type;
    }

    public void setType(EnumUtil.BillType type) {
        this.type = type;
    }

    public boolean isHasReceipt() {
        return hasReceipt;
    }

    public void setHasReceipt(boolean hasReceipt) {
        this.hasReceipt = hasReceipt;
    }

    public String getReceiptEetString() {
        return receiptEetString;
    }

    public void setReceiptEetString(String receiptEetString) {
        this.receiptEetString = receiptEetString;
    }


    public List<DiscountModel> getDiscounts() {
        return discounts;
    }

    public boolean isRemote() {
        return isRemote;
    }

    public void setRemote(boolean remote) {
        isRemote = remote;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public void setRecipeHTML(String recipeHTML) {
        this.recipeHTML = recipeHTML;
    }

    public EnumUtil.DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(EnumUtil.DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BranchModel getBranchModel() {
        return branchModel;
    }

    public void setBranchModel(BranchModel branchModel) {
        this.branchModel = branchModel;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Set<VatModel> getVatPrices() {
        return vatPrices;
    }

    public OrderItem getExtraCharge() {
        return extraCharge;
    }

    public void setExtraCharge(OrderItem extraCharge) {
        this.extraCharge = extraCharge;
    }

    public String getRecyclereceiptID() {
        return recyclereceiptID;
    }

    public void setRecyclereceiptID(String recyclereceiptID) {
        this.recyclereceiptID = recyclereceiptID;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category) {
        for (String categoryS : categories) {
            if (categoryS.equals(category)) {
                return;
            }
        }
        categories.add(category);
    }

    public String getSectorPlace() {
        return sectorPlace;
    }

    public void setSectorPlace(String sectorPlace) {
        this.sectorPlace = sectorPlace;
    }

    public OrderItem getTip() {
        return tip;
    }

    public void setTip(OrderItem tip) {
        this.tip = tip;
    }

    public boolean isStorno() {
        return isStorno;
    }

    public void setStorno(boolean storno) {
        isStorno = storno;
    }

    //TODo : fix obaly
    public void addItem(OrderItem newItem) {
        for (OrderItem oldItem : this.items) {
            if (oldItem.getCode().equals(newItem.getCode())) {
                if (!newItem.getNote().equals("")) {
                    items.add(newItem);
                    return;
                }
                if(newItem.getSideDish().size() >0){
                    items.add(newItem);
                    return;
                }
                if(newItem.getSideDish().size() == 0){
                    oldItem.setCount(oldItem.getCount()+1);
                    for (OrderItem cover : oldItem.getCover()) {
                        cover.setCount(cover.getCount()+1);
                    }
                    return;
                }
            }
        }
        items.add(newItem);
    }

    public void addSideDish(OrderItem main, OrderItem secondary) {
        for (OrderItem sideDish : main.getSideDish()) {
            if (sideDish.getCode().equals(secondary.getCode())) {
                sideDish.setCount(sideDish.getCount() + 1);
                return;
            }
        }
        main.getSideDish().add(secondary);
    }


    public VatModel getCorrespondingVatPricing(Double vatPrice) {
        for (VatModel vatModel : this.getVatPrices()) {
            if (vatModel.getVatPricing().equals(vatPrice)) {
                return vatModel;
            }
        }
        return null;
    }


    public String getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(String deliverAt) {
        this.deliverAt = deliverAt;
    }

    public List<String[]> getSimpleBillItems() {
        List<String[]> printList = new ArrayList<>();

        for (String category : getCategories()) {

            String[] categoryName = new String[]{category};
            printList.add(categoryName);

            for (OrderItem item : getItems()) {
                if (item.getCategory().equals(category)) {
                    String[] printItem = null;
                    if (SettingsManager.getInstance().isPrintProductCodes())
                        printItem = new String[]{item.getCode(), item.getCount() + "x " + item.getName()};
                    else
                        printItem = new String[]{item.getCount() + "x ", item.getName()};

                    printList.add(printItem);
                    if (!item.getNote().equals("")) {
                        printItem = new String[]{"", item.getNote(), ""};
                        printList.add(printItem);

                    }

                    for (OrderItem addition : item.getSideDish()) {
                        String[] printAdditionItem = null;
                        if (SettingsManager.getInstance().isPrintProductCodes())
                            printAdditionItem = new String[]{addition.getCode(), "+" + addition.getCount() + "x " + addition.getName(), "ADD"};
                        else
                            printAdditionItem = new String[]{"+ " + addition.getCount() + "x ", addition.getName(), "ADD"};
                        printList.add(printAdditionItem);
                    }

                    if (SettingsManager.getInstance().isPrintCoverBon()) {
                        for (OrderItem associated : item.getCover()) {
                            String[] printAssociatedItem = null;
                            if (SettingsManager.getInstance().isPrintProductCodes())
                                printAssociatedItem = new String[]{"", "+ " + associated.getCount() + "x " + associated.getName()};
                            else
                                printAssociatedItem = new String[]{"+ " + associated.getCount() + "x ", associated.getName()};
                            printList.add(printAssociatedItem);
                        }
                    }
                }
            }
        }
        return printList;
    }

    public List<String[]> getCartPrintList() {
        List<String[]> printList = new ArrayList<>();
        for (String category : BillManager.getInstance().getCurrentBill().getCategories()) {
            if (SettingsManager.getInstance().isPrintCategories()) {
                String[] categoryName = new String[]{category};
                printList.add(categoryName);
            }
            printProducts(printList, category);
        }
        return printList;
    }

    private void printProducts(List<String[]> printList, String category) {
        Context ctx = SettingsManager.getCtx();
        for (OrderItem item : getItems()) {
            if (item.getCategory().equals(category)) {
                double productSum;
                productSum = item.getCount() * item.getPrice();
                String[] printItem = null;
                if (SettingsManager.getInstance().isPrintProductCodes())
                    printItem = new String[]{item.getCode(), item.getCount() + "x " + item.getName() + "(" + String.format("%.2f", item.getPrice()) + BillManager.getInstance().getCurrency() + ")", String.format("%.2f", productSum) + " " + BillManager.getInstance().getCurrency()};
                else
                    printItem = new String[]{item.getCount() + "x", item.getName() + "(" + String.format("%.2f", item.getPrice()) + BillManager.getInstance().getCurrency() + ")", String.format("%.2f", productSum) + " " + BillManager.getInstance().getCurrency()};
                printList.add(printItem);

                for (OrderItem addition : item.getSideDish()) {
                    double additionSum;
                    additionSum = addition.getCount() * addition.getPrice();
                    String[] printAdditionItem = null;
                    if (SettingsManager.getInstance().isPrintProductCodes())
                        printAdditionItem = new String[]{addition.getCode(), "+ " + addition.getCount() + "x " + addition.getName() + "(" + String.format("%.2f", addition.getPrice()) + BillManager.getInstance().getCurrency() + ")", String.format("%.2f", additionSum) + BillManager.getInstance().getCurrency()};
                    else
                        printAdditionItem = new String[]{"+" + addition.getCount() + "x", addition.getName() + "(" + String.format("%.2f", addition.getPrice()) + BillManager.getInstance().getCurrency() + ")", String.format("%.2f", additionSum) + BillManager.getInstance().getCurrency()};
                    printList.add(printAdditionItem);
                }

                for (OrderItem associated : item.getCover()) {
                    double associatedSum;
                    associatedSum = associated.getCount() * associated.getPrice();
                    String[] printAssociatedItem = null;
                    if (SettingsManager.getInstance().isPrintProductCodes())
                        printAssociatedItem = new String[]{"", "+" + associated.getCount() + "x " + associated.getName() + "(" + String.format("%.2f", associated.getPrice()) + " " + BillManager.getInstance().getCurrency() + ")", String.format("%.2f", associatedSum) + BillManager.getInstance().getCurrency()};
                    else
                        printAssociatedItem = new String[]{"+" + associated.getCount() + "x", associated.getName() + "(" + String.format("%.2f", associated.getPrice()) + " " + BillManager.getInstance().getCurrency() + ")", String.format("%.2f", associatedSum) + BillManager.getInstance().getCurrency()};
                    printList.add(printAssociatedItem);
                }
                if (!item.getNote().equals("")) {
                    printItem = new String[]{"", item.getNote(), ""};
                    printList.add(printItem);
                }
            }
        }
    }

    public void getVatPricing() {

        double nullVatTotal = 0.00;
        double lowVatTotal = 0.00;
        double highVatTotal = 0.00;

        BillModel currentBill = BillManager.getInstance().getCurrentBill();
        for (OrderItem item : currentBill.getItems()) {
            VatModel vat = currentBill.getCorrespondingVatPricing(item.getVatPrice());
            for (Integer i = 0; i < item.getCount(); i++) {
                Double tmp = Double.valueOf(item.getPrice());
                vat.setSum(vat.getSum() + tmp);
            }

            for (OrderItem cover : item.getCover()) {
                if (item.getCover() != null) {
                    vat = currentBill.getCorrespondingVatPricing(cover.getVatPrice());
                    for (Integer i = 0; i < cover.getCount(); i++) {
                        Double tmp = Double.valueOf(cover.getPrice());
                        vat.setSum(vat.getSum() + tmp);
                    }
                }
            }

            for (OrderItem sideDish : item.getSideDish()) {
                vat = currentBill.getCorrespondingVatPricing(sideDish.getVatPrice());
                for (Integer i = 0; i < sideDish.getCount(); i++) {
                    Double tmp = Double.valueOf(sideDish.getPrice());
                    vat.setSum(vat.getSum() + tmp);
                }
            }
        }
        if (currentBill.getTransport() != null) {
            VatModel vat = currentBill.getCorrespondingVatPricing(currentBill.getTransport().getVatPrice());
            for (Integer i = 0; i < currentBill.getTransport().getCount(); i++) {
                Double tmp = Double.valueOf(currentBill.getTransport().getPrice());
                vat.setSum(vat.getSum() + tmp);
            }
        }
        if (currentBill.getTip() != null) {
            VatModel vat = currentBill.getCorrespondingVatPricing(currentBill.getTip().getVatPrice());
            for (Integer i = 0; i < currentBill.getTip().getCount(); i++) {
                Double tmp = Double.valueOf(currentBill.getTip().getPrice());
                vat.setSum(vat.getSum() + tmp);
            }
        }
        if (currentBill.getExtraCharge() != null) {
            VatModel vat = currentBill.getCorrespondingVatPricing(currentBill.getExtraCharge().getVatPrice());
            for (Integer i = 0; i < currentBill.getExtraCharge().getCount(); i++) {
                Double tmp = Double.valueOf(currentBill.getExtraCharge().getPrice());
                vat.setSum(vat.getSum() + tmp);
            }
        }

        for (VatModel vatPrice : currentBill.getVatPrices()) {
            if (vatPrice.getVatPricing() == 00.0) {
                nullVatTotal = vatPrice.getSum();
            } else if (vatPrice.getVatPricing() == 15.0) {
                lowVatTotal = vatPrice.getSum();
            } else if ((vatPrice.getVatPricing() == 21.0) || (vatPrice.getVatPricing() == 20.0)) {
                highVatTotal = vatPrice.getSum();
            }
        }
        Double discountsTotal = 0.00;
        for (DiscountModel discount : BillManager.getInstance().getCurrentBill().getDiscounts()) {
            double value = discount.getValue();
            if (discount.getValue() > 0)
                value = discount.getValue() * -1;
            discountsTotal += value;
        }
        discountsTotal *= -1;
        if (highVatTotal != 0.00 && discountsTotal != 0.00) {
            Double[] prices = returnUntilZero(highVatTotal, discountsTotal);
            highVatTotal = prices[0];
            discountsTotal = prices[1];
        }
        if (lowVatTotal != 0.00 && discountsTotal != 0.00) {
            Double[] prices = returnUntilZero(lowVatTotal, discountsTotal);
            lowVatTotal = prices[0];
            discountsTotal = prices[1];
        }
        if (nullVatTotal != 0.00 && discountsTotal != 0.0
        ) {
            Double[] prices = returnUntilZero(nullVatTotal, discountsTotal);
            nullVatTotal = prices[0];
        }

        for (VatModel vatPrice : currentBill.getVatPrices()) {
            if (vatPrice.getVatPricing() == 00.0) {
                vatPrice.setSum(nullVatTotal);
            } else if (vatPrice.getVatPricing() == 15.0) {
                vatPrice.setSum(lowVatTotal);
            } else if ((vatPrice.getVatPricing() == 21.0) || (vatPrice.getVatPricing() == 20.0)) {
                vatPrice.setSum(highVatTotal);
            }
        }

        VatModel toRemove = null;
        for (VatModel vatPrice : currentBill.getVatPrices()) {
            double total = vatPrice.getSum();
            if (total != 0.00) {
                double percent = vatPrice.getVatPricing();
                double vat = RoundTo2Decimals((total * (percent)) / (percent + 100));
                double vatBase = RoundTo2Decimals(total - vat);
                vatPrice.setBaseDPH(vatBase);
                vatPrice.setDphRest(vat);
            } else {
                toRemove = vatPrice;
            }
        }
        vatPrices.remove(toRemove);
    }

    double RoundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("#.00");
        return Double.valueOf(df2.format(val).replace(',', '.'));
    }

    private Double[] returnUntilZero(Double vatTotal, Double discountTotal) {
        while (vatTotal != 0.00 || discountTotal != 0.00) {
            if (vatTotal < 1.00) {
                discountTotal = discountTotal - vatTotal;
                return new Double[]{vatTotal, discountTotal};
            }
            if (discountTotal < 1.00) {
                vatTotal = vatTotal - discountTotal;
                return new Double[]{vatTotal, 0.0};
            }
            vatTotal--;
            discountTotal--;
        }
        return new Double[]{vatTotal, discountTotal};
    }


}

package cz.deepvision.pos.orderlibrary.models;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.util.Comparator;

import cz.deepvision.pos.orderlibrary.graphql.type.OrderOriginEnum;
import cz.deepvision.pos.orderlibrary.graphql.type.OrderStateCategoryEnum;

public class PrintArguments {
    private String orderID;
    private boolean typeKitchen;
    private Bitmap bitmapforPrint;
    private boolean smallBitmap = false;
    private OrderOriginEnum origin;
    private OrderStateCategoryEnum orderState;
    private boolean reprint = false;

    public PrintArguments() {
    }

    public PrintArguments(String orderID, boolean typeKitchen, OrderOriginEnum origin, OrderStateCategoryEnum orderState) {
        this.orderID = orderID;
        this.typeKitchen = typeKitchen;
        this.origin = origin;
        this.orderState = orderState;

    }

    public PrintArguments(String orderID, boolean reprint) {
        this.orderID = orderID;
        this.reprint = reprint;

    }

    public Bitmap getBitmapforPrint() {
        return bitmapforPrint;
    }

    public void setBitmapforPrint(Bitmap bitmapforPrint) {
        this.bitmapforPrint = bitmapforPrint;
    }


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public boolean isTypeKitchen() {
        return typeKitchen;
    }

    public void setTypeKitchen(boolean typeKitchen) {
        this.typeKitchen = typeKitchen;
    }

    public OrderOriginEnum getOrigin() {
        return origin;
    }

    public void setOrigin(OrderOriginEnum origin) {
        this.origin = origin;
    }

    public OrderStateCategoryEnum getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderStateCategoryEnum orderState) {

        this.orderState = orderState;
    }


    public boolean isReprint() {
        return reprint;
    }

    public void setReprint(boolean reprint) {
        this.reprint = reprint;

    }

    public boolean isSmallBitmap() {
        return smallBitmap;
    }

    public void setSmallBitmap(boolean smallBitmap) {

        this.smallBitmap = smallBitmap;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        PrintArguments arg = (PrintArguments) obj;
        if (this.getOrderID().equals(arg.getOrderID()) && (this.isTypeKitchen()) == arg.isTypeKitchen()) {
            return true;
        }
        return false;
    }
}

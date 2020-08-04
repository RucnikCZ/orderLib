package cz.deepvision.pos.orderlibrary.utils;

public class EnumUtil {

    public enum ProductType {
        MAIN,
        SIDEDISH,
        COVER,
        INTERNAL
    }

    public enum PriceType {
        INHOUSE,
        DELIVERY
    }

    public enum VatType {
        NOVAT,
        LOWVAT,
        HIGHVAT
    }

    public enum BillType {
        INCOMING,
        OPENED,
        CLOSED,
        CURRENT
    }

    public enum PaymentType {
        CASH,
        CARD,
        CASH_ONLINE
    }

    public enum PaymentStatus {
        PAID("ZAPLACENO"),
        UNPAID("NEZAPLACENO");
        String text;

        PaymentStatus(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }


    public enum OrderStatus {
        NEW,
        RECEIVED,
        COMPLETE,
        CANCELED
    }

    public enum UserRole {
        USER,
        CASHIER,
        MANAGER,
        ADMIN,
        SUPERADMIN
    }

    public enum SystemType {
        ENIGOO,
        SPEEDLO
    }

    public enum BillAction {
        OPEN,
        CANCEL,
        PRINT
    }

    public enum DeliveryType {
        PICK_UP,
        MESSENGER
    }
}

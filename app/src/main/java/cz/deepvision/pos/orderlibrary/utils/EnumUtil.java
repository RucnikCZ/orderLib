package cz.deepvision.pos.orderlibrary.utils;
public class EnumUtil {

    public enum ProductType{
        MAIN,
        SIDEDISH,
        COVER,
        INTERNAL
    }

    public enum PriceType{
        INHOUSE,
        DELIVERY
    }

    public enum VatType{
        NOVAT,
        LOWVAT,
        HIGHVAT
    }

    public enum BillType{
        INCOMING,
        OPENED,
        CLOSED,
        CURRENT
    }

    public enum PaymentType{
        CASH,
        CARD,
        CASH_ONLINE
    }

    public enum PaymentStatus{
        PAID,
        UNPAID
    }

    public enum OrderStatus{
        NEW,
        RECEIVED,
        COMPLETE,
        CANCELED
    }

    public enum UserRole{
        USER,
        CASHIER,
        MANAGER,
        ADMIN,
        SUPERADMIN
    }

    public enum SystemType{
        ENIGOO,
        SPEEDLO
    }

    public enum BillAction{
        OPEN,
        CANCEL,
        PRINT
    }

    public enum DeliveryType{
        PICK_UP,
        MESSENGER
    }
}

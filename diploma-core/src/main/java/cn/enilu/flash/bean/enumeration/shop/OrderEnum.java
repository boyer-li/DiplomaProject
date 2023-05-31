package cn.enilu.flash.bean.enumeration.shop;

/**
 * Order Status
 * @author ：enilu
 * @date ：Created in 11/6/2019 3:15 PM
 */
public class OrderEnum {
    public enum  OrderStatusEnum {
        UN_PAY(1, "Payment pending"),
        UN_SEND(2, "To be shipped"),
        SENDED(3, "Shipped"),
        FINISHED(4, "Completed"),
        CANCEL(5,"Canceled"),
        REFUND_ING(6,"Refund in progress"),
        REFUND(7,"Refunded");


        private String value;
        private Integer status;

        OrderStatusEnum(Integer status, String value) {
            this.status = status;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public Integer getId() {
            return status;
        }
    }

    public static String getStatusStr(Integer status){
        String statusStr = null;
        switch (status){
            case 1:
                statusStr = "unPay";
                break;
            case 2:
                statusStr = "unSend";
                break;
            case 3:
                statusStr = "sended";
                break;
            case 4:
                statusStr = "finished";
                break;
            case 5:
                statusStr = "cancel";
                break;
            case 6:
                statusStr = "refundIng";
                break;
            case 7:
                statusStr = "refund";
                break;
                default:
                    break;


        }
        return statusStr;

    }
    public static  Integer getStatusByStr(String statusStr){
        Integer state = null;
        switch (statusStr){
            case "unPay":
                state = 1;
                break;
            case "unSend":
                state =2;
                break;
            case "sended":
                state = 3;
                break;
            case "finished":
                state = 4;
                break;
            case "cancel":
                state = 5;
                break;
            case "refundIng":
                state = 6;
                break;
            case "refund":
                state = 7;
                break;
                default:
                    state = null;
                    break;

        }
        return state;
    }

    public static  OrderStatusEnum get(Integer status){
        for(OrderStatusEnum se : OrderStatusEnum.values()){
            if(se.getId().intValue() == status.intValue()){
                return se;
            }
        }
        return null;
    }

    public enum  PayTypeEnum {
        UN_PAY("alipay", "alipay"),
        UN_SEND("wxpay", "wechat_pay");


        private String value;
        private String key;

        PayTypeEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getKey() {
            return key;
        }
    }

    public static PayTypeEnum get(String key){
        for(PayTypeEnum payTypeEnum : PayTypeEnum.values()){
            if(payTypeEnum.getKey().equals(key)){
                return payTypeEnum;
            }
        }
        return null;
    }


    public enum  PayStatusEnum {
        UN_PAY(1, "Unpaid"),
        UN_SEND(2, "Paid"),
        PAYING(3, "In Payment");


        private String value;
        private Integer status;

        PayStatusEnum(Integer status, String value) {
            this.status = status;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public Integer getId() {
            return status;
        }
    }

    public static PayStatusEnum getPayStatus(Integer status){
        for(PayStatusEnum se : PayStatusEnum.values()){
            if(se.getId().intValue() == status.intValue()){
                return se;
            }
        }
        return null;
    }


}

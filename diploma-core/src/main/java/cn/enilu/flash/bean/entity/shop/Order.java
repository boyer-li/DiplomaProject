package cn.enilu.flash.bean.entity.shop;

import cn.enilu.flash.bean.entity.ShopBaseEntity;
import cn.enilu.flash.bean.entity.system.Express;
import cn.enilu.flash.bean.enumeration.shop.OrderEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ：enilu
 * @date ：Created in 2019/10/29 17:40
 */
@Data
@Table(appliesTo = "t_shop_order",comment = "订单")
@Entity(name="t_shop_order")
public class Order extends ShopBaseEntity {
    @Column(name="id_user",columnDefinition = "BIGINT COMMENT 'User id'")
    private Long idUser;
    @JoinColumn(name="id_user", referencedColumnName="id",insertable = false, updatable = false,foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private ShopUser user;
    @Column(columnDefinition = "VARCHAR(32) COMMENT 'Order Number'")
    private String orderSn;
    /**
     * @see OrderEnum.OrderStatusEnum
     */
    @Column(columnDefinition = "INT COMMENT 'Status'")
    private Integer status;
    /**
     * 收件人信息使用 下面三个字段(Recipient，收件人电话，收件地址）代替
     */
    @Deprecated
    @Column(name="id_address",columnDefinition = "BIGINT COMMENT 'Receiving Information'")
    private Long idAddress;
    /**
     *  收件人信息使用 下面三个字段(Recipient，收件人电话，收件地址）代替
     */
    @Deprecated
    @JoinColumn(name="id_address",  referencedColumnName="id",insertable = false, updatable = false,foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @Column(columnDefinition = "VARCHAR(32) COMMENT 'Recipient'")
    private String consignee;
    @Column(columnDefinition = "VARCHAR(16) COMMENT '收件人电话'")
    private String mobile;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '收件地址'")
    private String consigneeAddress;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_order")
    @org.hibernate.annotations.ForeignKey(name="none")
    @JsonManagedReference
    private List<OrderItem> items;
    @Column(columnDefinition = "VARCHAR(64) COMMENT 'Order Remarks'")
    private String message;
    @Column(columnDefinition = "VARCHAR(256) COMMENT '管理员备注'")
    private String adminMessage;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '配送费用'")
    private BigDecimal shippingAmount;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '交易金额'")
    private BigDecimal tradeAmount;
    @Column(columnDefinition = "VARCHAR(32) COMMENT 'Total amount'")
    private BigDecimal totalPrice;
    @Column(columnDefinition = "VARCHAR(32) COMMENT 'Coupon Credit Amount'")
    private BigDecimal couponPrice;
    @Column(columnDefinition = "VARCHAR(32) COMMENT 'Actual amount paid'")
    private BigDecimal realPrice;
    @Column(columnDefinition = "DATETIME COMMENT '出库时间'")
    private Date shippingTime;
    @Column(name="id_express",columnDefinition = "BIGINT COMMENT 'Courier Company'")
    private Long idExpress;
    @JoinColumn(name="id_express",  referencedColumnName="id",insertable = false, updatable = false,foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Express express;
    @Column(columnDefinition = "VARCHAR(32) COMMENT 'Courier Bill Number'")
    private String shippingSn;
    @Column(columnDefinition = "DATETIME COMMENT '确认收货时间'")
    private Date confirmReceivingTime;
    @Column(columnDefinition = "VARCHAR(16) COMMENT '实付类型:alipay,wechat'")
    private String payType;
    @Column(columnDefinition = "INT COMMENT '支付状态1:未付款;2:已付款,3:支付中'")
    private Integer payStatus;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '支付流水号'")
    private String payId;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '支付成功时间'")
    private Date payTime;


    public String getStatusName(){
        if(status!=null) {
            return OrderEnum.get(status).getValue();
        }
        return null;
    }
    public String getPayStatusName(){
        if(payStatus!=null) {
            return OrderEnum.getPayStatus(payStatus).getValue();
        }
        return null;
    }
    public String getPayTypeName(){
        if(payType!=null) {
            return OrderEnum.get(payType).getValue();
        }
        return null;
    }

    public Boolean hasPayed(){
        return OrderEnum.PayStatusEnum.UN_SEND.getId().equals(payStatus);
    }
}

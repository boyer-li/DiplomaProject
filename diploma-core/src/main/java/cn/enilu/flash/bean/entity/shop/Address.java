package cn.enilu.flash.bean.entity.shop;

import cn.enilu.flash.bean.entity.ShopBaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author ：enilu
 * @date ：Created in 2019/10/29 17:39
 */
@Data
@Table(appliesTo = "t_shop_address",comment = "Receiving Address")
@Entity(name="t_shop_address")
public class Address extends ShopBaseEntity {
    @Column(columnDefinition = "BIGINT COMMENT 'User id'")
    private Long idUser;
    @Column(columnDefinition = "VARCHAR(32) COMMENT 'Recipient'")
    private String name;
    @Column(columnDefinition = "VARCHAR(16) COMMENT 'Contact number'")
    private String tel;
    @Column(columnDefinition = "VARCHAR(16) COMMENT 'Province'")
    private String province;
    @Column(columnDefinition = "VARCHAR(16) COMMENT 'City'")
    private String city;
    @Column(columnDefinition = "VARCHAR(16) COMMENT 'District and County'")
    private String district;
    @Column(columnDefinition = "VARCHAR(16) COMMENT '地区编码'")
    private String areaCode;

    @Column(columnDefinition = "VARCHAR(64) COMMENT 'Detailed address'")
    private String addressDetail;
    @Column(columnDefinition = "VARCHAR(6) COMMENT 'Postal Code'")
    private String postCode;
    @Column(columnDefinition = "tinyint COMMENT 'Default or not'")
    private Boolean isDefault=false;
    @Column(columnDefinition = "tinyint COMMENT 'Whether to delete'")
    private Boolean isDelete= false;

    public String getWholeAddressInfo(){
        return getProvince()+" "+getCity()+getDistrict()+getAddressDetail();
    }

}

package cn.enilu.flash.bean.entity.shop;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author ：enilu
 * @date ：Created in 2019/10/29 17:39
 */
@Data
@Table(appliesTo = "t_shop_goods",comment = "Used goods")
@Entity(name="t_shop_goods")
@EntityListeners(AuditingEntityListener.class)
public class Goods extends BaseEntity {
    @NotBlank(message = "商品名称不能为空")
    @Column(columnDefinition = "VARCHAR(32) COMMENT 'Name'")
    private String name;
    @Column(columnDefinition = "VARCHAR(64) COMMENT 'Small picture'")
    private String pic;
    @Column(columnDefinition = "VARCHAR(512) COMMENT '大图相册列表,以逗号分隔'")
    private String  gallery;
    @Column(name="id_category",columnDefinition = "BIGINT COMMENT '类别id'")
    private Long idCategory;
    @JoinColumn(name="id_category", insertable = false, updatable = false,foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Product Description'")
    @NotBlank(message = "商品简介不能为空")
    private String descript;
    @Column(columnDefinition = "TEXT COMMENT '产品详情'")
    private String detail;
    /**
     * 如果商品是多规格，则存所有库存之和
     */
    @Column(columnDefinition = "INT COMMENT '库存数量'")
    private Integer stock=0;
    /**
     * 如果商品是多规格，则存最低价
     */
    @Column(columnDefinition = "VARCHAR(16) COMMENT 'Price'")
    private BigDecimal price;
    @Column(columnDefinition = "tinyint COMMENT 'Whether to delete'")
    private Boolean isDelete= false;
    @Column(columnDefinition = "tinyint COMMENT '是否上架'")
    private Boolean isOnSale = false;
    @Column(columnDefinition = "tinyint COMMENT 'New or old'")
    private Boolean isNew = false;
    @Column(columnDefinition = "tinyint COMMENT 'Hot or not'")
    private Boolean isHot = false;
    @Column(name="like_num",columnDefinition = "INT COMMENT '收藏数'")
    private Integer likeNum;

}

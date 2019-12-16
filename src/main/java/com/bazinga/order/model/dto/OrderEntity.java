package com.bazinga.order.model.dto;

import com.bazinga.base.model.entirty.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "bazinga_orders", catalog = "bazinga")
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer orderId;
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "BRAND_ID")
    private Integer brandId;
    @Column(name = "MODEL_ID")
    private Integer modelId;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "ENGINE_ID")
    private Integer engineId;
    @Column(name = "STATE")
    private Integer state;
    @Column(name = "IUSER")
    private Integer insertUser;
    @Column(name = "UUSER")
    private Integer updateUser;
    @Column(name = "IDATE")
    private Date insertDate;
    @Column(name = "UDATE")
    private Date updateDate;
}

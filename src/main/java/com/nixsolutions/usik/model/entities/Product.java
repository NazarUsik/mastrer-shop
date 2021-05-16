package com.nixsolutions.usik.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Product implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    String name;
    @Column(name = "TYPE_ID")
    long typeId;
    String alias;
    @Column(name = "SHOP_ID")
    long shopId;
    @Column(name = "STOCK_QUANTITY")
    long stockQuantity;
    double price;
    @Column(name = "DISCOUNT_PRICE")
    double discountPrice;
    @Column(name = "PHOTO_ID")
    long photoId;

    protected Product() {
    }
}

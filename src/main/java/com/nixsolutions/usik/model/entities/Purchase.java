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
import java.sql.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Purchase implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Column(name = "PURCHASE_DATE")
    Date purchaseDate;
    @Column(name = "STATUS_ID")
    long statusId;
    @Column(name = "TOTAL_PRICE")
    double totalPrice;
    @Column(name = "CLIENT_ID")
    long clientId;
    @Column(name = "PRODUCT_ID")
    long productId;
    long quantity;

    protected Purchase() {
    }
}

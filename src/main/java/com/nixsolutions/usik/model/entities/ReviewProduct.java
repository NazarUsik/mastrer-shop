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
@Entity(name = "REVIEW_PRODUCT")
public class ReviewProduct implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Column(name = "CLIENT_ID")
    long clientId;
    @Column(name = "PRODUCT_ID")
    long productId;
    String message;
    int rating;

    protected ReviewProduct() {
    }
}

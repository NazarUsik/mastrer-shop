package com.nixsolutions.usik.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Shop implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    String name;
    String alias;
    String address;
    String city;
    String state;
    String country;
    @Column(name = "ZIP_CODE")
    long zipCode;
    String email;
    @Column(name = "PHONE_NUMBER")
    String phoneNumber;
    @Column(name = "LOGO_ID")
    long logoId;
    @Column(name = "BANNER_ID")
    long bannerId;

    protected Shop() {
    }
}

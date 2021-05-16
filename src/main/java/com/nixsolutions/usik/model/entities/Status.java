package com.nixsolutions.usik.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Status implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    String name;

    protected Status() {
    }
}

package com.smartflow.model.entity;

import java.time.Instant;

import jakarta.persistence.Column;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
public abstract class BaseTimestampedEntity extends PanacheEntityBase {

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private Instant createDate;

    @UpdateTimestamp
    @Column(name = "modify_date", nullable = false)
    private Instant modifyDate;

}

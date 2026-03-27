package com.smartflow.model.entity;

import java.sql.Types;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

@Entity
@Table(name = "user", schema = "public")
@Setter
@Getter
public class UserEntity extends BaseTimestampedEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(schema = "public", name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @JdbcTypeCode(Types.NUMERIC)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    private Byte status;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

}

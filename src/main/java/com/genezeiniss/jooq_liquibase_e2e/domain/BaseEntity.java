package com.genezeiniss.jooq_liquibase_e2e.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    private String id;
    private LocalDateTime registeredOn;
}

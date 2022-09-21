package com.genezeiniss.jooq_liquibase_e2e.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Fish extends BaseEntity {

    private String fishermanId;
    private String type;
}

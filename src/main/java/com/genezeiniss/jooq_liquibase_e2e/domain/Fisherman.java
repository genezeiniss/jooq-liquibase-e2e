package com.genezeiniss.jooq_liquibase_e2e.domain;

import com.genezeiniss.jooq_liquibase_e2e.enums.FishermanLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Fisherman extends BaseEntity {

    private String name;
    private FishermanLevel level;
}

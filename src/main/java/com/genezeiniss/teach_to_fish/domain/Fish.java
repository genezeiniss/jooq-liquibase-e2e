package com.genezeiniss.teach_to_fish.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Fish extends BaseEntity {

    private String fishermanId;
    private String type;
}

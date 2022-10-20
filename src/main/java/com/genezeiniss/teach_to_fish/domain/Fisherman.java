package com.genezeiniss.teach_to_fish.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Fisherman extends BaseEntity {

    private String name;
}

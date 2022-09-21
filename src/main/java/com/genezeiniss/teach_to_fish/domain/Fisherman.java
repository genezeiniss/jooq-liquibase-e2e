package com.genezeiniss.teach_to_fish.domain;

import com.genezeiniss.teach_to_fish.enums.FishermanLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Fisherman extends BaseEntity {

    private String name;
    private FishermanLevel level;
}

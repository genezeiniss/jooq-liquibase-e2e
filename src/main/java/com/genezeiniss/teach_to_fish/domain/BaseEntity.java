package com.genezeiniss.teach_to_fish.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    private String id;
    private LocalDateTime registeredOn;
}

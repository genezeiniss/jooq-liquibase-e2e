package com.genezeiniss.teach_to_fish.service;

import com.genezeiniss.teach_to_fish.domain.Fish;
import com.genezeiniss.teach_to_fish.domain.Fisherman;
import com.genezeiniss.teach_to_fish.repository.FishRepository;
import com.genezeiniss.teach_to_fish.repository.FishermanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeachToFishService {

    private final FishermanRepository fishermanRepository;
    private final FishRepository fishRepository;

    public Fish fish() {
        Fisherman fisherman = createFisherman();
        return createFish(fisherman.getId());
    }

    private Fisherman createFisherman() {
        Fisherman fisherman = new Fisherman();
        fisherman.setName("Gene Zeiniss");
        return fishermanRepository.create(fisherman);
    }

    private Fish createFish(String fishermanId) {
        Fish fish = new Fish();
        fish.setFishermanId(fishermanId);
        fish.setType("The persistence layer in the Spring Boot project with automated data migration and active records generation");
        return fishRepository.create(fish);
    }
}

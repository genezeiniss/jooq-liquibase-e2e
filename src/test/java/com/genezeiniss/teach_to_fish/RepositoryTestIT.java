package com.genezeiniss.teach_to_fish;

import com.genezeiniss.teach_to_fish.domain.Fish;
import com.genezeiniss.teach_to_fish.domain.Fisherman;
import com.genezeiniss.teach_to_fish.repository.FishRepository;
import com.genezeiniss.teach_to_fish.repository.FishermanRepository;
import com.genezeiniss.teach_to_fish.service.TeachToFishService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RepositoryTestIT {

    @Autowired
    TeachToFishService teachToFishService;

    @Autowired
    FishermanRepository fishermanRepository;

    @Autowired
    FishRepository fishRepository;

    @Test
    @DisplayName("test fisherman and fish record creation on database")
    public void test() {
        Fish fish = teachToFishService.fish();

        Optional<Fisherman> optionalFisherman = fishermanRepository.findById(fish.getFishermanId());
        assertTrue(optionalFisherman.isPresent(), "fisherman record exist on database");
        assertEquals("Gene Zeiniss", optionalFisherman.get().getName(), "fisherman name");

        Optional<Fish> optionalFish = fishRepository.findById(fish.getId());
        assertTrue(optionalFish.isPresent(), "fish record exist on database");
        assertTrue(optionalFish.get().getType().contains("automated data migration and active records generation"), "fisherman type");
    }
}

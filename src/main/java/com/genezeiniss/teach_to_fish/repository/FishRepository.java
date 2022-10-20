package com.genezeiniss.teach_to_fish.repository;

import com.genezeiniss.teach_to_fish.domain.Fish;
import com.genezeiniss.teach_to_fish.persistence.generated.tables.records.FishRecord;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

import static com.genezeiniss.teach_to_fish.persistence.generated.Tables.FISH;

@Repository
public class FishRepository extends BaseRepository<Fish, FishRecord> {

    @Override
    public Table<FishRecord> getDBTable() {
        return FISH;
    }

    @Override
    public Class<Fish> getEntityType() {
        return Fish.class;
    }
}

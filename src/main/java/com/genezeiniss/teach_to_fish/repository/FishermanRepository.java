package com.genezeiniss.teach_to_fish.repository;

import com.genezeiniss.teach_to_fish.domain.Fisherman;
import com.genezeiniss.teach_to_fish.persistence.generated.tables.records.FishermanRecord;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

import static com.genezeiniss.teach_to_fish.persistence.generated.Tables.FISHERMAN;

@Repository
public class FishermanRepository extends BaseRepository<Fisherman, FishermanRecord> {

    @Override
    public Table<FishermanRecord> getDBTable() {
        return FISHERMAN;
    }

    @Override
    public Class<Fisherman> getEntityType() {
        return Fisherman.class;
    }
}

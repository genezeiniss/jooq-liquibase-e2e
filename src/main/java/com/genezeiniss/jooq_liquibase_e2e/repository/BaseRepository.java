package com.genezeiniss.jooq_liquibase_e2e.repository;

import com.genezeiniss.jooq_liquibase_e2e.domain.BaseEntity;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.TableRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public abstract class BaseRepository<E extends BaseEntity, R extends TableRecord<?>> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DSLContext db;

    public abstract Table<R> getDBTable();

    public abstract Class<E> getEntity();

    @SneakyThrows
    public String create(E entity) {
        entity.setId(String.valueOf(UUID.randomUUID()));
        R record = db.newRecord(getDBTable());
        modelMapper.map(entity, record);
        record.insert();
        return entity.getId();
    }
}

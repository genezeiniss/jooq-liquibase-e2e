package com.genezeiniss.teach_to_fish.repository;

import com.genezeiniss.teach_to_fish.domain.BaseEntity;
import com.genezeiniss.teach_to_fish.exception.NonUniqueResultException;
import com.genezeiniss.teach_to_fish.repository.util.DbUtil;
import lombok.SneakyThrows;
import org.jooq.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseRepository<E extends BaseEntity, R extends TableRecord<?>> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DSLContext db;

    protected Function<String, Condition> findByPrimaryKeyConditionProvider;

    public abstract Table<R> getDBTable();

    public abstract Class<E> getEntityType();

    @PostConstruct
    private void init() {
        findByPrimaryKeyConditionProvider = initFindByPrimaryKeyConditionProvider();
    }

    @SneakyThrows
    public E create(E entity) {
        entity.setId(String.valueOf(UUID.randomUUID()));
        R record = db.newRecord(getDBTable());
        modelMapper.map(entity, record);
        record.insert();
        return entity;
    }

    protected Function<String, Condition> initFindByPrimaryKeyConditionProvider() {
        Field<?> idField = DbUtil.getPrimaryKeyField(getDBTable());
        Class<?> idFieldType = idField.getType();
        if (String.class.isAssignableFrom(idFieldType)) {
            //noinspection unchecked
            return ((Field<String>) idField)::in;
        }

        throw new RuntimeException(
                String.format("id field of table %s is of unsupported type %s", getDBTable().getName(), idFieldType));
    }

    @SneakyThrows
    public Optional<E> findById(String id) {
        return findOne(selectFrom().where(findByPrimaryKeyConditionProvider.apply(id)));
    }

    protected Optional<E> findOne(ResultQuery<? extends Record> query) {
        List<E> convertedResults = find(query).collect(Collectors.toList());
        if (convertedResults.size() > 1) {
            throw new NonUniqueResultException(query);
        }
        return convertedResults.isEmpty() ? Optional.empty() : Optional.of(convertedResults.get(0));
    }

    protected Stream<E> find(ResultQuery<? extends Record> query) {
        return query.fetch().stream().map(
                record -> modelMapper.map(record, getEntityType()));
    }

    protected SelectWhereStep<?> selectFrom() {
        return db.selectFrom(getDBTable());
    }
}

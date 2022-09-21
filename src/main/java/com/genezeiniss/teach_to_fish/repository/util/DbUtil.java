package com.genezeiniss.teach_to_fish.repository.util;

import org.jooq.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class DbUtil {

    /**
     * Returns the {@link Field} representing the primary key of the given table, assuming it has exactly one PK field
     *
     * @param table The given table
     * @return The id field
     * @throws IllegalArgumentException if PK is missing or composed of more than one field
     */
    public static TableField<?, ?> getPrimaryKeyField(Table<?> table) {
        UniqueKey<?> primaryKey = table.getPrimaryKey();
        // Get a non-empty list of fields from table pk or throw exception
        List<? extends TableField<?, ?>> primaryKeyFields = Optional.ofNullable(primaryKey)
                .map(Key::getFields)
                .filter(fields -> !fields.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("table %s does not have a primary key. Expected a single field primary key", table.getName())));

        if (primaryKeyFields.size() > 1) {
            throw new IllegalArgumentException(
                    String.format("table %s primary key is composed of more than one field: %s. Expected a single field primary key",
                            table.getName(), primaryKeyFields.stream().map(Field::getName).collect(joining(","))));
        }

        return primaryKeyFields.get(0);
    }
}

-- liquibase formatted sql

-- changeset gzeiniss:1.0
CREATE TABLE IF NOT EXISTS enum_fisherman_level
(
    level INT PRIMARY KEY,
    name  VARCHAR(32) NOT NULL UNIQUE
);

-- changeset gzeiniss:1.1
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM enum_fisherman_level
INSERT INTO enum_fisherman_level
VALUES (1, 'JUNIOR'),
       (2, 'SENIOR'),
       (3, 'STAFF');

-- changeset gzeiniss:1.2
CREATE TABLE IF NOT EXISTS fisherman
(
    id            VARCHAR(36) PRIMARY KEY,
    name          VARCHAR(264),
    level         VARCHAR(32),
    registered_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_level FOREIGN KEY (level) REFERENCES enum_fisherman_level (name)
);

-- changeset gzeiniss:1.3
CREATE TABLE IF NOT EXISTS fish
(
    id            VARCHAR(36) PRIMARY KEY,
    fisherman_id  VARCHAR(36) NOT NULL,
    type          LONGTEXT    NOT NULL,
    registered_on TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_fish_fisherman_id FOREIGN KEY (fisherman_id) REFERENCES fisherman (id)
);

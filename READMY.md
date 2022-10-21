# Teach To Fish Project

In this project, our fish 🐟 is the persistence layer in the Spring Boot project with automated data migration and
active records generation.

Involved libraries and plugins: `java`,`spring-boot`, `maven`, `liquibase-maven-plugin`, `mysql-connector-java`
, `jooq-codegen-maven`, `jooq-metedata-extention-liquibase` and `modelmapper`.

## How to start MySql on Podman container

Install podman
`$ brew install podman`

Start podman
`$ podman machine init`

`$ podman machine start`

Download MySQL podman Iimage
`$ podman pull mysql:8`

Run MySQL database instance in a container

```
$ podman run -d -it --name mysql-8 -p 23306:3306 \
-e MYSQL_DATABASE=fisherman_db \
-e MYSQL_ROOT_PASSWORD=fisherman \
-e MYSQL_USER=fisherman \
-e MYSQL_PASSWORD=teachamantofish \
docker.io/library/mysql:8
```

## How to run Liquibase update

### Format

```
mvn liquibase:update \
-Dliquibase.url="${driver}://${host}/${database}" \
-Dliquibase.username="${username}" \
-Dliquibase.password="${password}"
```

### Example

```
mvn liquibase:update \
-Dliquibase.url="jdbc:mysql://localhost:23306/fisherman_db" \
-Dliquibase.username="fisherman"  \
-Dliquibase.password="teachamantofish"
```

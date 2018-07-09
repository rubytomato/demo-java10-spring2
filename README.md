# Spring Boot 2.0 Rest API Application

Development environment

* OpenJDK 10.0.1
* Spring Boot 2.0.3
* MySQL CE 8.0.11
* Maven 3.5.3

```text
> java -version
openjdk version "10.0.1" 2018-04-17
OpenJDK Runtime Environment (build 10.0.1+10)
OpenJDK 64-Bit Server VM (build 10.0.1+10, mixed mode)
```

## compile

```text
mvn clean package
```

## run

### executable jar

```text
java -jar .\target\demo.jar
```

Specify a profile

```text
java -jar -Dspring.profiles.active=dev .\target\demo.jar
```

### spring boot maven plugin

```text
mvn spring-boot:run
```

Specify a profile

```text
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### API

#### get

```text
curl -v "http://localhost:9000/app/memo/1"
```

```text
curl -v "http://localhost:9000/app/memo/list"
```

```text
curl -v "http://localhost:9000/app/memo/list?page=0&size=5"
```

**example**

```text
> curl "http://localhost:9000/app/memo/2"
{
  "id" : 2,
  "title" : "memo job",
  "description" : "memo2 description",
  "done" : false,
  "updated" : "2018-01-04T13:02:10"
}
```

#### post

```text
curl -v -H "Content-Type:application/json" -d "{\"title\": \"new memo title\", \"description\": \"new memo description\", \"done\": false }" -X POST "http://localhost:9000/app/memo"
```

or

```text
curl -v -H "Content-Type:application/json" -d @new_memo.json -X POST "http://localhost:9000/app/memo"
```

new_memo.json

```json
{
    "title": "new memo title",
    "description": "new memo description",
    "done": false
}
```

#### patch

```text
curl -v -H "Content-Type:application/json" -d "{\"id\": 1, \"title\": \"update memo title\", \"description\": \"update memo description\" }" -X PATCH "http://localhost:9000/app/memo"
```


#### delete

```text
curl -v -X DELETE "http://localhost:9000/app/memo/1"
```

## database

**database**

resources/scripts/sql/create_database.sql

```sql
CREATE DATABASE IF NOT EXISTS demo_db
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;
```

**user**

resources/scripts/sql/create_user.sql

```sql
CREATE USER IF NOT EXISTS 'demo_user'@'localhost'
  IDENTIFIED BY 'demo_pass'
  PASSWORD EXPIRE NEVER;

GRANT ALL ON demo_db.* TO 'demo_user'@'localhost';
```

### Using Memo API

**table**

resources/scripts/sql/create_memo_table.sql

switch to demo_db.

```sql
DROP TABLE IF EXISTS memo;

CREATE TABLE IF NOT EXISTS memo (
  id BIGINT AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  done BOOLEAN NOT NULL DEFAULT FALSE,
  updated TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET = utf8mb4,
COLLATE utf8mb4_general_ci;
```

**create test data**

resources/scripts/sql/insert_memo_data.sql

```sql
INSERT INTO memo (id, title, description, done, updated) VALUES
  (1, 'memo shopping', 'memo1 description', false, '2018-01-04 12:01:00'),
  (2, 'memo job', 'memo2 description', false, '2018-01-04 13:02:10'),
  (3, 'memo private', 'memo3 description', false, '2018-01-04 14:03:21'),
  (4, 'memo job', 'memo4 description', false, '2018-01-04 15:04:32'),
  (5, 'memo private', 'memo5 description', false, '2018-01-04 16:05:43'),
  (6, 'memo travel', 'memo6 description', false, '2018-01-04 17:06:54'),
  (7, 'memo travel', 'memo7 description', false, '2018-01-04 18:07:05'),
  (8, 'memo shopping', 'memo8 description', false, '2018-01-04 19:08:16'),
  (9, 'memo private', 'memo9 description', false, '2018-01-04 20:09:27'),
  (10,'memo hospital', 'memoA description', false, '2018-01-04 21:10:38')
;
```

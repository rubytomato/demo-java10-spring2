# Spring Boot 2.0 Rest API Application

Development environment

* Java 10.0.1
* Spring Boot 2.0.2
* MySQL CE 8.0.11
* Maven 3.5.3

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


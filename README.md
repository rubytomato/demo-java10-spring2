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


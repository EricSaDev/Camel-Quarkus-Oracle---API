# camel-quarkus-oracle-api Project

This project is an API that writes and queries an object (Key.java) in Oracle Database. This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Deploy on localhost and Podman

* Create a Oracle Container
  
  ```shell script
  podman pull absolutapps/oracle-12c-ee

  podman images 

  mkdir /home/eric/dbdata/oracle12c/oradata

  podman run -d --name oracle --privileged -v /home/eric/dbdata/oracle12c/oradata:/u01/app/oracle -p 8080:8080 -p 1521:1521 oracle-12c-ee

  podman logs oracle

  podman exec -it oracle /bin/bash

  sqlplus system/oracle@localhost:1521/orcl

  SQL*Plus: Release 12.1.0.2.0 Production on Tue Sep 27 19:27:57 2022

  Copyright (c) 1982, 2014, Oracle.  All rights reserved.

  Connected to:
  Oracle Database 12c Enterprise Edition Release 12.1.0.2.0 - 64bit Production
  With the Partitioning, OLAP, Advanced Analytics and Real Application Testing options

  SQL> CREATE TABLE people(people_id NUMBER GENERATED BY DEFAULT AS IDENTITY,firstname VARCHAR2(15) NOT NULL,lastname VARCHAR2(15) NOT NULL,email VARCHAR2(25) NOT NULL,start_date DATE NOT NULL, end_date DATE NOT NULL,PRIMARY KEY(people_id));

  Table created.

  SQL> COMMIT WORK;

  Commit complete.

  SQL> insert into people (firstname, lastname, email, start_date, end_date) values ('Osvaldo','Melo','omelo@redhat.com',DATE '2017-11-14',DATE '2017-11-16');

  1 row created.

  SQL> COMMIT WORK;

  Commit complete.

  SQL> set wrap off;

  SQL> SET LINESIZE 200;

  SQL> select * from people;
  ```

* Deploy Camel Quarkus APP
  
  ```shell script
  ./mvnw clean package -Dquarkus.kubernetes.deploy=true
  ```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/camel-quarkus-oracle-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- OpenShift ([guide](https://quarkus.io/guides/deploying-to-openshift)): Generate OpenShift resources from annotations
- Camel Core ([guide](https://access.redhat.com/documentation/en-us/red_hat_integration/2.latest/html/camel_extensions_for_quarkus_reference/extensions-core)): Camel core functionality and basic Camel languages: Constant, ExchangeProperty, Header, Ref, Ref, Simple and Tokenize
- Camel SQL ([guide](https://access.redhat.com/documentation/en-us/red_hat_integration/2.latest/html/camel_extensions_for_quarkus_reference/extensions-sql)): Perform SQL queries
- Camel JDBC ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/jdbc.html)): Access databases through SQL and JDBC
- Camel Rest ([guide](https://access.redhat.com/documentation/en-us/red_hat_integration/2.latest/html/camel_extensions_for_quarkus_reference/extensions-rest)): Expose REST services and their OpenAPI Specification or call external REST services

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

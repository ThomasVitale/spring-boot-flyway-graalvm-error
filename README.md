# spring-boot-flyway-graalvm-error

## Pre-requisites

* Java 25
* Podman/Docker

## How to reproduce

Build the application using the following command:

```shell
./gradlew nativeCompile
```

Run a PostgreSQL container with Podman or Docker:

```shell
podman compose up -d
# or
docker compose up -d
```

Start the application:

```shell
./build/native/nativeCompile/spring-boot-flyway-graalvm-error
```

You should see an error similar to the following:

```log
2026-03-15T20:46:28.937+01:00  WARN 56417 --- [demo] [           main] s.s.c.ServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'flywayInitializer': org.flywaydb.core.internal.exception.sqlExceptions.FlywaySqlServerUntrustedCertificateSqlException.isFlywaySpecificVersionOf(java.sql.SQLException)
2026-03-15T20:46:28.937+01:00  INFO 56417 --- [demo] [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
Application run failed
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'flywayInitializer': org.flywaydb.core.internal.exception.sqlExceptions.FlywaySqlServerUntrustedCertificateSqlException.isFlywaySpecificVersionOf(java.sql.SQLException)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1817)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:603)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:525)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:333)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:371)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:331)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:196)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:196)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.instantiateSingleton(DefaultListableBeanFactory.java:1218)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingleton(DefaultListableBeanFactory.java:1184)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:1121)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:994)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:621)
        at org.springframework.boot.web.server.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:143)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:756)
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:445)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:321)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1365)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1354)
        at com.example.demo.DemoApplication.main(DemoApplication.java:12)
        at java.base@25/java.lang.invoke.LambdaForm$DMH/sa346b79c.invokeStaticInit(LambdaForm$DMH)
Caused by: java.lang.NoSuchMethodException: org.flywaydb.core.internal.exception.sqlExceptions.FlywaySqlServerUntrustedCertificateSqlException.isFlywaySpecificVersionOf(java.sql.SQLException)
        at java.base@25/java.lang.Class.checkMethod(DynamicHub.java:1343)
        at java.base@25/java.lang.Class.getMethod(DynamicHub.java:1337)
        at org.flywaydb.core.internal.exception.FlywaySqlException.throwFlywayExceptionIfPossible(FlywaySqlException.java:81)
        at org.flywaydb.core.internal.jdbc.JdbcUtils.openConnection(JdbcUtils.java:67)
        at org.flywaydb.core.internal.jdbc.JdbcConnectionFactory.<init>(JdbcConnectionFactory.java:76)
        at org.flywaydb.core.FlywayExecutor.execute(FlywayExecutor.java:142)
        at org.flywaydb.core.Flyway.migrate(Flyway.java:186)
        at org.springframework.boot.flyway.autoconfigure.FlywayMigrationInitializer.afterPropertiesSet(FlywayMigrationInitializer.java:67)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1864)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1813)
        ... 21 more
```

## How to solve it

Uncomment the `@ImportRuntimeHints` in the `DemoApplication` class and then follow again the steps to reproduce. The application should start without any error.

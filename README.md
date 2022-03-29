# Table of Contents
1. [Set up](#set-up)
2. [The code](#the-code)
3. [The tests](#the-tests)
4. [Environments and deployment](#environment-and-deployment)


# Set up <a name="set-up"></a>
# The code <a name="the-code"></a>
# The tests <a name="the-tests"></a>
# Environments and deployment <a name="environment-and-deployment"></a>

# offx-middleware

This repo uses **git submodules** to manage the dependencies of the off-x-libs. This allows for the off-x-libs to be used without the dependencies being installed.
This is useful for development and testing. Cloe the repo like this:

    git clone https://git.clarivate.io/scm/offx/off-x-middleware.git
    git submodule init
    git submodule update

## Java version

This service uses Java 17 version. You need to install it on your machine: https://openjdk.java.net/projects/jdk/17/

If you use the environment variable JAVA_HOME, you need to point it to the Java 17 version. Now the code should compile on your IDE.

## Start on localhost

1. Start ssh tunnel to the database on the port 10102
2. Several ways to start on local:
    1. On terminal, run: ./gradlew clean build bootRun
    2. On terminal, run: ./gradlew clean build && java --enable-preview -jar build/libs/off-x-middleware.jar
    3. On your IDE, run the gradle task "bootRun" (you can run it on debug mode)
    4. On your IDE, run the Java main class: OffxMiddleware.java (again, you can use debug mode)

The app starts on port 8080, you can check the endpoint:

    http:localhost:8080/ping

## Spring profiles

To start the server for snapshot/stable/prod, run:

    java -jar -Dspring.profiles.active=snapshot build/libs/off-x-middleware.jar

## Swagger

You can access API documentation on

    http:localhost:8080/swagger-ui.html


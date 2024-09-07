ARG GRADLE_IMG=gradle:jdk17
ARG JAVA_IMG=openjdk:17-jdk-slim

FROM $GRADLE_IMG AS builder
WORKDIR /workdir
COPY build.gradle .
COPY settings.gradle .
COPY gradle.properties .
COPY src src

ENV GRADLE_OPTS -Dorg.gradle.daemon=false

RUN gradle build -x check

FROM $JAVA_IMG
ARG DEPENDENCY=/workdir/build

COPY --from=builder ${DEPENDENCY}/libs/module4-1.jar /opt/securedApp/securedApp.jar

WORKDIR /opt/securedApp
CMD java -jar securedApp.jar
FROM maven:3-jdk-8-alpine

COPY src/ /usr/share/svenis/src
COPY pom.xml /usr/share/svenis/
COPY LICENSE /usr/share/svenis/

WORKDIR /usr/share/svenis

CMD mvn flyway:migrate package exec:java -Dexec.mainClass=com.svenis.Main

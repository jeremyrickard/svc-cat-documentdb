FROM openjdk:8-jre

ADD build/libs/svc-cat-documentdb-0.0.1-SNAPSHOT.jar .

EXPOSE 8080
CMD ["java", "-jar", "svc-cat-documentdb-0.0.1-SNAPSHOT.jar"]

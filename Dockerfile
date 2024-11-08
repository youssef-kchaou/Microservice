FROM openjdk:17
EXPOSE 8282
ADD target/CourzeloProject-0.0.1-SNAPSHOT.jar CourzeloProject.jar
ENTRYPOINT ["java", "-jar","CourzeloProject.jar"]
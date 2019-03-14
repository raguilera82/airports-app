FROM openjdk:8-jdk-slim

EXPOSE 8080

COPY target/*.jar ./app.jar

ENTRYPOINT ["java", \
    "-Djava.security.egd=file:/dev/./urandom ", \
    "-Xmx512m", \
    "-jar", "/app.jar" \
]


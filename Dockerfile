FROM eclipse-temurin:17-jre-alpine

EXPOSE 8081

WORKDIR /application

COPY target/SpringBootLearn-0.0.1-SNAPSHOT.jar testcont.jar

RUN java -XX:ArchiveClassesAtExit=app.jsa -Dspring.context.exit=onRefresh -jar testcont.jar & exit 0

ENV JAVA_CDS_OPTS="-XX:SharedArchiveFile=app.jsa -Xlog:class+load:file=/tmp/classload.log"
ENV JAVA_ERROR_FILE_OPTS="-XX:ErrorFile=/tmp/java_error.log"

ENTRYPOINT java \
    $JAVA_ERROR_FILE_OPTS \
    $JAVA_CDS_OPTS \
    -jar testcont.jar
FROM lwieske/java-8
VOLUME /tmp
EXPOSE 9449
ADD service-management-rest.jar app.jar
RUN sh -c "ls -ltr "
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
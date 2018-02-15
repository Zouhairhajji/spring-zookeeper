FROM lwieske/java-8
VOLUME /tmp
EXPOSE 8080
ADD service-management-web.jar app.jar
RUN sh -c "ls -ltr "
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
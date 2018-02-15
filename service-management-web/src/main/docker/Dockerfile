FROM lwieske/java-8
VOLUME /tmp
EXPOSE 9500
ADD service-management-web.jar app.jar
RUN sh -c "ls -ltr "
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
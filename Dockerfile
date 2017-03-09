FROM clojure:lein-2.7.1-alpine

WORKDIR /usr/src/app

# Add source code
COPY project.clj ./
COPY src src
COPY resources resources

    # Install lein
RUN apk add --update tzdata && \
    
    # Set timezone
    TZ=Europe/Vienna && \
    cp /usr/share/zoneinfo/$TZ /etc/localtime && \
    echo $TZ > /etc/timezone && \
    
    # Build JAR file
    lein deps && \
    mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" store.jar && \
    chmod +x store.jar && \
    mv store.jar /bin/ && \
    
    # Cleanup
    apk del tzdata && \
    rm -rf project.clj src resources /tmp/* /var/cache/apk/*

# Start store
EXPOSE 80
CMD    ["java", "-jar", "/bin/store.jar"]

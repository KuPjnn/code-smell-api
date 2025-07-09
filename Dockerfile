FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /app
COPY target/*-runner /app/application
EXPOSE 8002
ENTRYPOINT ["/app/application", "-Dquarkus.http.host=0.0.0.0"]
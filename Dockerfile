FROM ubuntu:22.04
WORKDIR /app
COPY target/*-runner /app/application
EXPOSE 8002
ENTRYPOINT ["/app/application", "-Dquarkus.http.host=0.0.0.0"]
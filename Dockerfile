FROM maven:latest
MAINTAINER Semih Saydam
RUN mkdir -p /usr/src/app
EXPOSE 8080
WORKDIR /usr/src/app
ADD . /usr/src/app
ENTRYPOINT mvn clean test -Dtest=ApiTestRunner
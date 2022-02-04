# Midas Rest Assured

### Dependencies

* log4j
* cucumber
* rest-assured
* wiremock (for unit tests)
* junit

## Authors
 Semih Saydam

## Feb 4
- Dockerfile
  - docker image build -t semihsaydamz/restassured .
  - docker container run --name rest semihsaydamz/restassured
    - (reWork) docker container start rest
    - docker container logs -f rest

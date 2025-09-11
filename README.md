# Required Technologies:
- Java 17 Correto
- Maven 3.9.11
- SpringBoot 3.5.5
- Docker
- Docker compose
- Mysql 8
- OpenApi - Swagger

# how build and run
- mvn clean install
- docker-compose up --build

# user basic authentication
- user: admin, password: secret
- db: victor , tu_password
- all data are coming from docker compose env variables

# run instructions
-1 get token api with my spotify client and secret, endpoint
http://localhost:8080/api/token

-3 create track in system
http://localhost:8080/api/track/create?isrc=USEE10001993
you should pass header token = Bearer {your_token}
as we can see in evidence documentation

-4 with same isrc you can get the saved data sample
http://localhost:8080/api/track/metadata/USEE10001993

-5 with same isrc you can download the cover image
http://localhost:8080/api/track/cover/USEE10001993

all steps are presented in the evidence images

# swagger and openapi endpoints for testing
- http://localhost:8080/swagger-ui/index.htm
- http://localhost:8080/api-docs
- I made all my testing using swagger web app

# Evidence folder
[Evidence_docs](Evidence_docs)

# Thanks :)

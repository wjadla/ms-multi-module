# Made By
wael Jadla

# spring-boot-microservices
This repository contains the latest source code of the spring-boot-microservices application that i build using the latest technos

# run docker keycloak
docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev

# run docker zipkin
docker run -d -p 9411:9411 openzipkin/zipkin

# run docker kafka
docker compose up -d
docker logs -f broker
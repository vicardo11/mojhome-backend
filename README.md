# Mojhome - backend
This is the backend part of the MojHome application.

## Logging
Logging is handled using the ELK Stack (Elasticsearch, Logstash, Kibana) - Docker containers.

## Authentication server
Keycloak is used as an authentication provider. It runs as a Docker container. Backend services connect to the Keycloak server to validate if the token delivered in the requests is valid.

## Ports
- 8100 - Keycloak server
- 5601 - Kibana
- 9200 - Elasticsearch
- 8200 - Finance service



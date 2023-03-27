#!/bin/sh

wget https://raw.githubusercontent.com/camunda/camunda-platform/main/docker-compose-core.yaml -O docker-compose.yaml
touch connector-secrets.txt

docker compose up
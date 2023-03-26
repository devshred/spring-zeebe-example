# Sample SpringBoot-App using Camunda Platform 8
This shows how to set up and run Camunda Platform 8 locally.

## start infrastructure
```shell
cd infrastructure
./start-cluster.sh
```

## start _"application"_
```shell
mvn spring-boot:run
```

## start process
[trigger HTTP request](src/test/http/requests.http)

## watch dashboards
* add operate.local to /etc/hosts
* open http://operate.local

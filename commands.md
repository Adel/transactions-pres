# Completable future
apache tomcat 7: 10 000 default NIO threads

## use task executor
curl -X POST http://localhost:8080/job/async 
## use nio thread
curl -X POST http://localhost:8080/job/sync 

## update grower
curl -H "Content-Type: application/json" -X PATCH http://localhost:8080/grower/1 -d '{"name": "tata"}'


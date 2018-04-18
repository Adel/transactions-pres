# Completable future
apache tomcat 7: 10 000 default NIO threads

## use task executor
curl -X POST http://localhost:8080/job/async 
## use nio thread
curl -X POST http://localhost:8080/job/sync 




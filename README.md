# microreactive
Reactive micro for getting data streams

https://www.evernote.com/shard/s518/sh/3d404d38-d20d-48d6-b65f-654b5120b235/decc712152ced11bfd3e0bdccd59c2b7

Integration with Prometheus

API services:

http://localhost:8085/api/hello             --> Checking health service hello world
http://localhost:8085/actuator/prometheus   --> To see metrics
http://localhost:8085/api/numeros           --> Generate Flux numbers

http://127.0.0.1:8085/api/updateSalary      --> Update salary value (See Postman PUT examples)
http://127.0.0.1:8085/api/updateExpenses    --> Update expenses value (See Postman PUT examples)
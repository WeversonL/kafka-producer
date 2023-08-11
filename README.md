<h1 align="center">
   Kafka Producer
</h1>

This API is responsible for publishing the means of payment in the queue of Apache Kafka

## Technologies
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Kafka](https://docs.spring.io/spring-kafka/reference/html/)
- [Docker](https://docs.docker.com/get-started/)

## Practices adopted

- SOLID
- API REST
- Sending data in Message Broker queues
- Dependency Injection
- Automated tests
- Containerization with docker

## Get started

To upload the API in an automated way, use the repository: [kafka-messaging](https://github.com/WeversonL/kafka-messaging)

## API Endpoints

To make the HTTP requests below, the [httpie](https://httpie.io) tool was used:

- Create a payment 
```
$ http POST :8081/payment customerName="Weverson L" paymentMethod="C" paymentAmount=1000.0

[
  {
    "id": "2b7df20b-69ff-44a7-8f7d-530acad43c7d",
    "customerName": "Weverson L",
    "paymentAmount": 1000.0,
    "paymentMethod": "CREDIT",
    "status": "ANALYSIS"
  }
]
```

## License

`Kafka Producer` is [MIT licensed](LICENSE).
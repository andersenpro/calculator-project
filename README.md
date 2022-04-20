---
**Calculator-Project** é um serviço web REST implementado em Java utilizando a 
estrutura Spring Boot. O RabbitMQ foi utilizado para as comunicações entre os
módulos que existentes. O serviço web suporta as seguintes operações matemáticas:
- Adição
- Subtração
- Multiplicação
- Divisão

### Dependencies
- Java 11
- Spring Boot 2.6
- Logback-access 1.2
- Lombok 1.18

### Miscelaneous
- IDE IntelliJ Community

## Rabbitmq docker container
### Criar o container rabbitmq no docker
```
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:3-management
```
### Iniciar o container rabbitmq se já foi criado no docker.
```
docker start rabbitmq
```

### Build project
```
mvn clean package -DskipTests
```

### Run test
```
mvn test
```

### Run project
```
mvn -T 2 -pl calculator-rest,calculator spring-boot:run
```


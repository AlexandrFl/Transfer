## Сервис перевода денег
### Работа сервиса:
1. В метод makeTransfer(Transferinfo transfer) класса Controller поступают исходные данные FRONT
```java
public class Controller {
    
    @PostMapping(path = "/transfer", consumes = {"application/json"})
    public ResponseEntity<Object> makeTransfer(@Valid @RequestBody TransferInfo transfer) {
        if (transfer.getCARD_FROM_NUMBER().equals(transfer.getCARD_TO_NUMBER())) {
            return new Response().generateWrongResponse("НОМЕРА КАРТЫ СОВПАДАЮТ", HttpStatus.NOT_FOUND);
        }
        return service.makeTransfer(transfer);
    }
}
```
Если полученные данные валидны и номер карты получатела не совпадает с номером карты отправителя, то объект Transferinfo передается в качестве параметра в метод makeTransfer класса Service.
При совпадении номеров карт клиенту отправляется ответ с HttpStatus.INTERNAL_SERVER_ERROR (500).
Если данные не валидны, то клиенту направляется ответ с HttpStatus.BAD_REQUEST (400) и указанием на невалидность исходных данных.

2. Из метода класса Service объект Transferinfo, через конструктор метода makeTransfer попадает в Repository.
```java
public class Repository {
    
    public ResponseEntity<Object> makeTransfer(TransferInfo transfer) {
        saver = new Saver(numberOfOperation.getAndIncrement(), operationId.get(), transfer);
        logger.log(saver.toString());
        logger.log("Transfer status - " + HttpStatus.OK);
        return new Response().generateOkResponse(operationId.get(), HttpStatus.OK);
    }
}
```
В репозитории трансферу присваевается номер операции (numberOfOperation) и id операции (operationId).
На основе номера операции, id операции и информации о переводе (TransferInfo) создается объект класса Saver, которых хранит в себе всю информацию о транзакции.
Затем происходит логгирование объекта класса Sever в файл ServerLog.txt.
В качестве результата работы метода клиенту направляется ответ с id операции и HttpStatus.OK.
Так как в данной работе не рассматривается наличие или отсутствие достаточных средств на карте, все запросы данные которых валидны возвращают ответ с HttpStatus.OK.
3. После получения ответа на запрос "/transfer" клиент получает id операции и в теле запроса "/confirm" отправляет на сервер id операции и секретный код. Эти данные поступают в метод confirmTransfer класса Controller и за тем через класс Service попадают в Repository.
```java
public class Repository {

    public ResponseEntity<Object> confirmTransfer(Confirm confirm) {
        if (!saver.getSaverByOperationId(Integer.parseInt(confirm.getOperationId()))) {
            logger.log("Confirming status - " + HttpStatus.INTERNAL_SERVER_ERROR);
            return new Response().generateWrongResponse("Ошибка подтверждения операции", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.log("Confirming status - " + HttpStatus.OK);
        return new Response().generateOkResponse(operationId.getAndIncrement(), HttpStatus.OK);
    }
}
```

4. В методе confirmTransfer(Confirm confirm) класса Repository происходит сравнение id операции полученого из объекта класса Confirm с id операции находящемся в классе Saver.
При совпадении id данные логируются и клиенту напрвляется ответ с HttpStatus.OK.
Если же id не совпали, то клиенту направляется ответ HttpStatus.INTERNAL_SERVER_ERROR и сообщением об ощибке. Эта информация так же логгируется.

### Запуск
Для запуска FRONT части проекта был написан Dockerfile с содержанием:
```java
FROM node:11.15.0-alpine
WORKDIR /usr/app/front
EXPOSE 3000 //Порт на котором страница перевода доступна в браузере
COPY ./ ./
RUN npm install
CMD ["npm", "start"]
```
Для запуска серверной части был написан Dockerfile с содержанием:
```java
FROM openjdk:8-jdk-alpine
EXPOSE 8090 // Порт по которому идет обмен данных между клиентом и сервером
ADD target/transfer-0.0.1-SNAPSHOT.jar j.jar
ENTRYPOINT ["java", "-jar", "/j.jar"]
```
Затем были созданы образы клиентской и серверной части в Docker и с помощью docker-compose.yml были запущены два контейнера.
Содержание docker-compose.yml:
```java
version: "2"

services:
  server:
    image: "s"
    ports:
      - "8090:8090"
    container_name: "server"

  front:
    image: "front"
    ports:
      - "3000:3000"
    container_name: "front"
```
### Результат работы
![](https://sun9-north.userapi.com/sun9-82/s/v1/ig2/MJZHKcByqpl12hIxZVihcbu8EX9Sp4qmqDODRU8l-eG9uFh6VTiiRI-BYlfK8URvMtFFIAouKGMqXIc11hAsEUaP.jpg?size=1914x977&quality=95&type=album)
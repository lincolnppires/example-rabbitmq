# Exemplo RabbitMQ

Alguns exemplos de uso do software de mensageria RabbitMQ.

Através da aplicação é possível avaliar:

* Algumas formas de produzir e consumir messagens
* Diferentes tipos de exchange, 
* Dead Letter Exchange e Time to Live
* Uso AmqpRejectAndDontRequeueException, classe exception para rejeitar mensagens 
* Uso Message, que representa toda a mensagem, não apenas o conteúdo
* Channel, meio de comunicação entre o Java e RabbitMQ
* Retry Mechanism


#### Tecnologias utilizadas

* Java 11
* RabbitMQ 3.7.15
* Project Lombok

#### RabbitMQ

* Inicializar serviço

```
sudo docker run -d --restart always --name rabbitmq --hostname docker-rabbitmq -p 5672:5672 -p 15672:15672 -v /home/lincoln/rabbitmq/docker/data:/var/lib/rabbitmq/mnesia rabbitmq:management

```

#### Configurações da queue, exchange e rabbitmq: 
	
* [application.yml](https://github.com/lincolnppires/example-rabbitmq/blob/master/src/main/resources/application.yml)
	
* [AmqpConfiguration.java](https://github.com/lincolnppires/example-rabbitmq/blob/master/src/main/java/com/example/config/AmqpConfiguration.java)
	
* [AmqpConfigurationRetryDirectExchange.java](https://github.com/lincolnppires/example-rabbitmq/blob/master/src/main/java/com/example/config/AmqpConfigurationRetryDirectExchange.java)	
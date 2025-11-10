# Guia de Construção do MVP de Microsserviços

Este repositório contém uma implementação de exemplo do MVP de conversão de moedas com autenticação e autorização.  Ele foi estruturado com base nos roteiros da disciplina de Engenharia de Software II e no enunciado do trabalho final.  Cada microsserviço está em um diretório próprio, e há um arquivo `docker-compose.yml` para orquestrar a execução de todos os componentes.

## Pré‑requisitos

- **Java 17/21 SDK** – necessário para compilar os projetos Spring Boot;
- **Maven 3.9+** – usado para build e gerenciamento de dependências;
- **Docker** e **Docker Compose** – para empacotar e rodar os serviços em contêineres.

## Estrutura dos diretórios

- `naming-server` – servidor de registro de serviços utilizando **Eureka**.
- `api-gateway` – gateway de entrada implementado com **Spring Cloud Gateway** e filtro de circuit breaker (**Resilience4J**).
- `currency-exchange-service` – microsserviço de câmbio que gerencia as taxas de conversão.
- `currency-conversion-service` – microsserviço que calcula conversões a partir das taxas de câmbio; publica eventos na fila de mensagens.
- `history-service` – serviço consumidor que grava o histórico de conversões recebidas via **RabbitMQ**.
- `docker-compose.yml` – orquestra todos os serviços incluindo o **RabbitMQ** e o **Keycloak**.

## Passo a passo de construção

### 1 — Construir cada microsserviço

Em cada subdiretório execute o comando Maven para compilar e empacotar o serviço em um JAR executável:

```bash
cd naming-server
mvn clean package
cd ../api-gateway
mvn clean package
cd ../currency-exchange-service
mvn clean package
cd ../currency-conversion-service
mvn clean package
cd ../history-service
mvn clean package
cd ..
```

Cada comando `mvn clean package` produz um arquivo `target/…​-SNAPSHOT.jar`.

### 2 — Construir as imagens Docker

Execute os comandos abaixo na raiz do repositório.  Eles utilizam os `Dockerfile`s de cada serviço para gerar imagens locais:

```bash
docker build -t naming-server:latest naming-server
docker build -t api-gateway:latest api-gateway
docker build -t currency-exchange:latest currency-exchange-service
docker build -t currency-conversion:latest currency-conversion-service
docker build -t history-service:latest history-service
```

### 3 — Iniciar o ambiente com docker compose

O arquivo `docker-compose.yml` define todos os serviços e suas dependências.  Para iniciar o ambiente inteiro (incluindo Keycloak e RabbitMQ) execute:

```bash
docker compose up -d --build
```

Isso irá compilar as imagens, iniciar o Keycloak (exposto em `http://localhost:9090`), o Eureka (8761), o gateway (8080) e os serviços de câmbio (8000), conversão (8100) e histórico (8200), além do RabbitMQ (15672/5672).  Após a inicialização, configure o Keycloak criando um realm `currency`, as roles `USER` e `ADMIN`, os clientes (`gateway` e `microservices`) e os usuários conforme descrito no relatório.

### 4 — Encerrar o ambiente

Para parar e remover os contêineres execute:

```bash
docker compose down
```

Os volumes e redes podem ser removidos com `docker compose down -v --remove-orphans`.

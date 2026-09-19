# Setup do Banco de Dados (PostgreSQL via Docker)

Este projeto usa PostgreSQL rodando em um container Docker. Cada pessoa do time sobe o **próprio** banco local — os dados não são compartilhados entre máquinas, só a estrutura das tabelas (via Flyway).

## Pré-requisitos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado e aberto (aguarde aparecer "Engine running")
- IntelliJ com o projeto já clonado (`git pull` da branch principal)

## Passo a passo

### 1. Carregar as dependências do Maven

Ao abrir o projeto, se aparecer um ícone flutuante no canto superior direito do `pom.xml` ("Load Maven Changes"), clique nele. Isso baixa o Spring Data JPA, o driver do PostgreSQL e o Flyway.

Se não aparecer automaticamente, abra o painel **Maven** (lateral direita do IntelliJ) e clique em **Reload All Maven Projects**.

### 2. Subir o banco de dados

No terminal, na raiz do projeto (onde está o `compose.yaml`), rode:

```bash
docker compose up -d
```

Na primeira vez, o Docker baixa a imagem do Postgres — pode demorar um pouco. Nas próximas, sobe em segundos.

### 3. Confirmar que o banco está rodando

```bash
docker ps
```

Deve aparecer uma linha com `IMAGE: postgres:latest` e `STATUS: Up`.

### 4. Rodar a aplicação

Com o container ativo, é só rodar a aplicação Spring Boot normalmente. O Flyway vai criar as tabelas automaticamente na primeira execução.

## Credenciais do banco local

| Configuração | Valor |
|---|---|
| Host | `localhost` |
| Porta | `5432` |
| Banco | `chickenpix` |
| Usuário | `myuser` |
| Senha | `secret` |

Essas credenciais já estão configuradas no `application.properties` do projeto — não é necessário alterar nada, só ter o container rodando.

## Comandos úteis

| Comando | O que faz |
|---|---|
| `docker compose up -d` | Sobe o banco em segundo plano |
| `docker compose down` | Desliga o banco (mantém os dados salvos) |
| `docker compose down -v` | Desliga o banco **e apaga os dados** (reset total) |
| `docker ps` | Lista containers rodando |

## Problemas comuns

**"Port 5432 already in use"**
Você já tem outro Postgres rodando na sua máquina (local ou outro container). Pare o outro serviço, ou mude a porta do host no `compose.yaml` (ex: `'5433:5432'`) e ajuste a porta no `application.properties` também.

**Dependências do Maven continuam vermelhas no IntelliJ**
Clique no ícone "Load Maven Changes" que aparece ao editar o `pom.xml`, ou force manualmente pelo painel Maven > Reload All Maven Projects.

**Erro do Flyway não reconhecendo o Postgres**
Confirme que a dependência `flyway-database-postgresql` está no `pom.xml` — nas versões recentes do Flyway, o suporte a Postgres foi separado do `flyway-core`.

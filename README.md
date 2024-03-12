# Tech Challenge FIAP - Green Trace Food Lanches
Projeto Fase 2 da pós graduação da FIAP de Arquitetura de Software

### Grupo 2 - Membros:
* RM352611 - José Ronaldo Leles Júnior - email: juniorleles80@gmail.com
* RM352829 - Saulo Carvalho Gomes - email: sgomesnet@gmail.com
* RM352875 - Thiago de Melo Pereira - email: thiagomelop@yahoo.com.br
* RM353122 - Fabrício Guimarães de Oliveira - email: fguimaraesoliveira@gmail.co


## Documentação - Fase 1 / Fase 2
* [Wiki - Notion](https://grupo2-techchallenge.notion.site/fda6d866c084429fb7257c1ffc9f12d6?v=703211fd8fc34d9d8b0e2b4da5c3415e)

## Dependências
* [IntelliJ IDEA (Opcional)](https://www.jetbrains.com/idea/download/#section=windows)
* [Java JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
* [Spring Boot 3.1.0](https://spring.io/projects/spring-boot)
* [PostgreSql](https://www.postgresql.org/download/)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/)
* [Postman](https://www.postman.com/)

## Executando aplicação completa via docker

Execute o comando abaixo para iniciar os containers com a base de dados e executar a aplicação localmente.

```bash
docker-compose up -d
```

## Endpoints
Para visualizar os endpoints disponíveis na aplicação na Fase 2 basta acessar o swagger em [http://localhost:31000/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Sequencia de endpoints para atender a entrega
1. Checkout Pedido - /pedidos/checkout
2. Consultar o status pagamento pedido - /pedidos/{codigo}/consultar
3. Webhook para receber confirmação de pagamento aprovado ou recusado - /pagamentos/webhook/{codigoPedido}
4. Lista de pedidos - /pedidos
5. Atualizar o status do pedido - /pedidos/{codigo}/alterar-status

## Postman
Segue o link  da collection do postman ( separada por fase): https://github.com/FabricioGuimaraesOliveira/fiap-techchallange/tree/feature/clean-architeture/docs/postman

## Kubernetes
Para visualizar os arquivos de configuração: https://github.com/FabricioGuimaraesOliveira/fiap-techchallange/tree/feature/clean-architeture/kubernetes

##Para executar usando a arquitetura do Kubernetes:

Video : https://youtu.be/ymaYeJpU9Rc

1) Instalar o Docker Desktop;
2) Habilitar em Configurações -> Kubernetes -> Habilitar Kubernetes
3) Executar no projeto pela linha de comando \kubernetes\startup.bat
4) Conferior pela linha de comando: kubectl get pods
5) Abrir o navegador e verificar o swagger
6) Consumir os endpoints em http://localhost:31000/



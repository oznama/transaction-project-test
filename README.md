# Aplicación web Transacciones con Angular y Java (Spring Boot)

Una vez los microservicios y la aplicación angular se esten ejecutando, se puede acceder a las siguientes rutas

### Aplicación web Angular

- [Index](http://localhost:4200/)
- Usuario: guest
- Password: 1234

### Microservicio Auth
- [Swagger](http://localhost:8080/ms-auth/api/v1/swagger-ui/index.htm)
- [H2](http://localhost:8080/ms-auth/api/v1/h2-console)
- JDBC URL: jdbc:h2:mem:soaintdb_auth
- Usuario: root
- Password: 1234

### Microservicio Main (Transacciones)
- [Swagger](http://localhost:8080/ms-auth/api/v1/swagger-ui/index.htm)

### Microservicio Database (Transacciones)
- [Swagger](http://localhost:8082/ms-db/api/v1/swagger-ui/index.html)
- [H2](http://localhost:8082/ms-db/api/v1/h2-console)
- JDBC URL: jdbc:h2:mem:soaintdb
- Usuario: admin
- Password: 1234

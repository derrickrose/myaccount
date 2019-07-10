spring:
  application:
    name: myaccount
  datasource:
#==============================================
#    driver-class-name: com.mysql.jdbc.Driver
#    url: jdbc:mysql://localhost:3306/myaccount
#    username: root
#    password:
#=================================================
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/postgres?createDatabaseIfNotExist=true
    username: postgres
    password: postgres
  jpa:
    generate-ddl: true
    show-sql: true
#    hibernate:
    ddl-auto: create-drop
#===========================
#  h2:
#    console:
#      enabled: true
#      path: /h2-console
#============================
#    properties:
#      dialect: org.hibernate.dialect.MySQL5Dialect
server:
  port: 8080
currency:
  converter:
    url: https://api.exchangeratesapi.io/latest?base=
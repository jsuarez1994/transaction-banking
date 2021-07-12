# transaction-banking
Prueba MICROSERVICIOS con Java 8 y Spring


La base de datos esta apuntando a un cluster gratuito, esta es la url: https://api.elephantsql.com/

He utilizado para realizar el proyecto:
- Spring Framework (2.5.2)
- org.apache.commons.
- lombok.
- mapstruct.
- Swagger
- Datos en una BD Postgresql.


Al levantar el servidor, lo hara en el 8080 y se podra probar en la ruta http://localhost:8080/swagger-ui.html

Las transacciones creadas previamente para pruebas son las siguientes:
ID  ACCOUNT IBAN              AMOUT   DATE                    DESCRIPTION         FEE   REFERENCE
1	  ES9820385778983000760236	1000	  2021-07-11 10:58:17.397	primera transaccion	100	  04453M
2	  ES9820385778983000760236	-400	  2021-07-12 11:00:51.066	segundatransaccion	14	  01455U
3	  ES9820385778983000760230	100	    2021-07-13 10:56:24.929	Movimiento nuevo	  20	  09638C

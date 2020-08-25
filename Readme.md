#Eshop
##General information
Eshop application providing access to pet products.
Currently implemented features:
- list product (supports filtering and paging)
- retrieve single product
- register eshop user
- authenticate eshop user
- create new order
- list all orders for current user

##Technical information
Application is implemented as a Spring boot based web application (running on embedded Tomcat). 
Data is stored in Postgresql database.

###Interface
The only available interface is REST Api:
- public endpoints (retrieve product, product list, create user, authenticate user)
- private endpoints, that require authentication (create order, list orders)
Inputs are validated using JSR-303/JSR-349 Bean Validations.

####Security
Security is implemented using Spring security and JWT. Upon registration (POST to `/users`), each user is required to 
provide username and password. These credentials are then used to obtain JWT via `/authenticate` endpoint, which in turn 
returns JWT that is required for private endpoints.

####Persistence
Data is persisted in Postgresql via Spring JPA and Hibernate. Database structure is set up using Flyway migration scripts
located in `resources/db/migration` folder. Connection to DB is configured in `application.yml` file in `resources` directory.
Application requires DB to be up and running prior to start.

####Build
Application is built using Maven. Usual `mvn clean install` will provide executable jar file (with `-exec.jar` suffix) 
located in `target` directory. 

To build a Docker image, maven profile `docker-image` must be activated.

####Run
To run executable jar file, execute `java -jar target/eshop-1.0-SNAPSHOT-exec.jar`.

To run as a Docker container, execute `docker run tmp-repo/eshop:1.0-SNAPSHOT`
TechTrack
TechTrack develops a platform for monitoring and managing technical equipment such as industrial robots, manufacturing equipment, quality control systems, etc. However, at the moment the company has a problem with managing data on the technical condition of equipment.

Features
Equipment monitoring and management
Data validation and security
Integration with PostgreSQL for data storage
API documentation using OpenAPI
JWT-based authentication
Installation
Clone the repository:

<img align="center" alt="Java " width="550px" src="https://res.cloudinary.com/software-updater/image/upload/v1716740204/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2024-05-26_221106_azolya.png" />  <br />
    <br />

<img align="center" alt="Java " width="550px" src="https://res.cloudinary.com/software-updater/image/upload/v1716740204/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2024-05-26_221035_lxxkyy.png" />  <br />
    <br />

<img align="center" alt="Java " width="550px" src="https://res.cloudinary.com/software-updater/image/upload/v1716740204/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2024-05-26_220959_vpodpd.png" />  <br />
    <br /> 

<img align="center" alt="Java " width="550px" src="https://res.cloudinary.com/software-updater/image/upload/v1716740514/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2024-05-26_222132_lwpk6p.png" />  <br />
    <br />     

    
Example configuration:

properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/techtrack
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your-secret-key
Usage
Once the application is running, you can access the API documentation at http://localhost:8080/swagger-ui.html.

Spring Boot Starters:
spring-boot-starter-validation
spring-boot-starter-security
spring-boot-starter-data-jpa
spring-boot-starter-web
spring-boot-starter-test (test scope)
SpringDoc OpenAPI:
springdoc-openapi-starter-webmvc-ui
PostgreSQL JDBC Driver:
postgresql
Lombok:
lombok (optional)
JSON Web Token (JWT):
jjwt-api
jjwt-impl
jjwt-jackson
Spring Security Test:
spring-security-test (test scope)
Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Make sure to follow the coding standards and include relevant tests.

License
This project is licensed under the MIT License. See the LICENSE file for details.


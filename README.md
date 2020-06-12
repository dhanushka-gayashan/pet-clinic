# pet-clinic


## Dependencies
- Developer Tools
    - Spring Boot DevTools
    - Lambok
    
- Web
    - Spring Web
    
- Template Engines
    - Thymeleaf
    
- SQL
    - Spring Data JPA
    - H2 Database
    - MySQL Driver
    
- Ops
    - Spring Boot Actuator


## Frontend Technologies
- Less
- WRO4J


## Unit Testing Tools
- JUnit 5
- Mockito
- MockMVC
- Maven-Failsafe-Plugin (for Integration Testing)


## CI Integration
- Circle CI

 
## Configuration
- Create GitHub Repo
- Configure SSH in Local to GitHub


## Local Build
- Clean the Project

    `mvn clean`

- Packaged the Project

    `mvn package`

- Run Application

## Release New Build for Deployment
- Prepare the Build
    - Clean the project
        
        `mvn clean`
    
    - Push all changers into the Repo
    
       ` git add --all`
       
       `git commit -m '<commit-message>'`
       
       `git push origin master`
    
    - Prepare the Build
    
        `mvn release:prepare`

- Release the  Build
    - Maven automatically Tag all modules on GitHub
    
    - Create the release
    
        `mvn release:perform`

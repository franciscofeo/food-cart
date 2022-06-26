
# Food Cart 🛒

This project was created to practice and learn about Event-Sourcing and CQRS Pattern
simulating the behavior of a Shopping Cart present in most e-commerces.

Based in the tutorial of [Arquiteto das Galáxias](https://www.youtube.com/c/ArquitetodasGal%C3%A1xias) YouTube channel.


## Tech Stack

* [Axon Framework](https://www.axoniq.io/)
* [Kotlin](https://kotlinlang.org/)
* [Spring Framework](https://spring.io/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [H2 Database](https://www.h2database.com/html/main.html)

## License

[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/tterb/atomic-design-ui/blob/master/LICENSEs)


## Run Locally

Clone the project

```bash
    git clone https://github.com/franciscofeo/food-cart
```

Go to the project directory

```bash
    cd food-cart
```

Run the Makefile command

```bash
    make up
```

If you don't have a IDE like IntelliJ or Eclipse, you can run this command with Gradle
to start the application

```bash
    ./gradlew bootRun
```

To stop the application use these commands

```bash
    ./gradlew -stop
```

and

```bash
    make down
```

The application will run in the port :8080 and the Axon GUI Dashboard in :8024. 
# customer-api

This is a simple API for registering customers with a security layer using Bearer Token. This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `customer-api-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/customer-api-1.0.0-SNAPSHOT-runner.jar`.

## API Documentation

OpenAPI
http://localhost:8080/openapi

Swagger-UI
http://localhost:8080/swagger-ui/


## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/customer-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Run using docker container

see more at: https://hub.docker.com/r/nettooe/customer-api-jvm

```
docker run -d -p 8080:8080 --env MYSQL_USERNAME=<username> --env MYSQL_PASSWORD=<password> --env MYSQL_URL=<url> --name customer-api nettooe/customer-api-jvm:1.0.1
```

## Playing with a running instance

1 - Access the swagger interface at:

http://ec2co-ecsel-1w3awmkxgmj5m-375907991.us-east-1.elb.amazonaws.com:8080/swagger-ui/

2 - Expanda o o grupo "user" e clique em "Try it out"

![step_002](https://github.com/nettooe/customer-api/blob/master/docs/step_002.png?raw=true)

3 - Into 'Request Body', type as shown below and then click the "Execute" button.

```
{
  "password": "user",
  "username": "user"
}
```
![step_003](https://github.com/nettooe/customer-api/blob/master/docs/step_003.png?raw=true)

4 - In the body of the answer, copy the value shown in the "token" key.

![step_004](https://github.com/nettooe/customer-api/blob/master/docs/step_004.png?raw=true)

5 - Click on the "Authorize" button at the top of the page on the right and then paste the contents of the token into the dialog that will open.

![step_005](https://github.com/nettooe/customer-api/blob/master/docs/step_005.png?raw=true)

6 - There, you have just completed the step-by-step required by the security layer and are authorized to try the other endpoints. If a 401 status error occurs, then repeat the previous steps to generate a new token.


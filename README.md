# Introduction
The repository already contains the compiled files for both the React and the Java applications so re-compilation is **not required**. The server can be started by using the Dockerfile attached to create a Docker image and container and run it using the instructions provided below. An alterantive would be to run the `webapp/target/webapp-1.0.jar` directly on the server if JRE and Tomcat are installed and configured (instructions not provided, JAR can be executed with `java -jar webapp/target/webapp-1.0.jar`).

There are unit tests testing the fuzzy matching logic for the test names with several examples to check the tuning for the matching threshold ratio. 

# Technical Instructions
## Running Instructions

### Docker Installation
Install the Docker engine on the destination system. Refer to Docker documentation for the relevant OS instructions:
* [Docker Desktop for Mac](https://docs.docker.com/docker-for-mac/install/)
* [Docker Desktop for Windows](https://docs.docker.com/docker-for-windows/install/)
* [Docker Engine for Linux](https://docs.docker.com/engine/install/)

### Docker Image & Application Startup
1. Run the Docker build from the root folder (containing the Dockerfile and the `webapp/` and `spa/` folders) using the command: `docker build -t hhassignment:latest .`.
3. Create the Docker container: `docker run -p 8080:8080 hhassignment:latest`
4. Access the application from `http://localhost:8080`

* **If port 8080 is unavailable on the host system and cannot be used, change the exposed port to whichever port is available. For example, using port `8067` run the following command instead for step 3  `docker run -p 8067:8080 hhassignment:latest` and then access the application on `http://localhost:8067`**

## Usage Instructions
The application is a single page app allowing the user to input a *Test Name* and a *Test Result Value* and send a request to the server to try and find a matching test and evaluate the value. The server will respond with the processed result and it will be displayed to the user.
Basic form validation is implemented in the webpage and it will not allow the user to send empty inputs or a non numeric value for the *Test Result Value* input.

![Screenshot](https://user-images.githubusercontent.com/18195803/112762592-17015200-9009-11eb-9514-790c1eab89fa.png)

## Compilation and packaging instructions

### React Compilation (requires NPM)
1. Compile the React Single Page Application by running `npm run build` from the `/spa` folder.
2. Copy the contents of the `spa/build/` folder into `/webapp/src/main/resources/static/`.

### Java Compilation and packaging (requires Maven)
Run the Maven build from the `webapp/` folder with the command `mvn clean install`. The build produces the executable `webapp/target/webapp-1.0.jar`.

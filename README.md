## Running Instructions

### Docker Installation
Install the Docker engine on the destination system. Refer to Docker documentation for the relevant OS instructions:
* [Docker Desktop for Mac](https://docs.docker.com/docker-for-mac/install/)
* [Docker Desktop for Windows](https://docs.docker.com/docker-for-windows/install/)
* [Docker Engine for Linux](https://docs.docker.com/engine/install/)

### Docker Image & Application Startup
1. Run the Docker build from the root folder (containing the Dockerfile and the `webapp` and `spa` folders) using the command: `docker build -t hhassignment:latest .`.
3. Create the Docker container: `docker run -p 8080:8080 hhassignment:latest`
4. Access the application from `http://localhost:8080`

* **If port 8080 is unavailable on the host system and cannot be used, change the exposed port to whichever port is available. For example, using port `8067` run the following command instead for step 3  `docker run -p 8067:8080 hhassignment:latest` and then access the application on `http://localhost:8067`**

## Compilation and packaging instructions

### React Compilation 
1. Compile the React Single Page Application by running `npm run build` from the `/spa` folder.
2. Copy the contents of the `/spa/build/` folder into `/webapp/src/main/resources/static/`.

### Java Compilation and packaging 
1. Run the Maven build from the `/webapp` folder to generate the `.jar` file: `mvn clean install`.


## Instructions

### React Compilation (can be skipped if compiled files are available in the resources folder in the `webapp`)
1. Compile the React Single Page Application by running `npm run build` from the `/spa` folder.
2. Copy the contents of the `/spa/build/` folder into `/webapp/src/main/resources/static/`.

### Java Compilation and packaging (can be skipped if .jar is available)
1. Run the Maven build from the `/webapp` folder to generate the `.jar` file: `mvn clean install`.

### Docker Image & Application Startup
1. Run the Docker build from the root folder using the Dockerfile: `docker build -t hhassignment:latest .`.
2. Create the Docker container: `docker run -p 8080:8080 hhassignment:latest`
3. Access the application from `http://localhost:8080`

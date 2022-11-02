# weather-orchestration-service
Test project for MeaWallet

How to run Weather-orchestration-service app.
1. Specify User-Agent in Environment Variables in IDE Run configurations like <User-Agent=[your user agent];>
2. Specify port in config.properties(default port is 8083)
3. Run WeatherOrchestrationServerApplication main method
4. Endpoint to request - http://localhost:[your port]/weather?lat=[your latitude]&lon=[your longitude]
5. Parameter for latitude must be number from -90 till 90, for longitude from -180 till 180

Technologies used:
1. Java 17
2. Gradle
3. Spring Boot
4. In-Memory DataBase H2
5. Lombok

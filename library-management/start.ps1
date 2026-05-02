$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.18.8-hotspot"
Write-Host "Setting JAVA_HOME to $env:JAVA_HOME" -ForegroundColor Green
Write-Host "Starting Spring Boot application..." -ForegroundColor Cyan
.\mvnw.cmd spring-boot:run

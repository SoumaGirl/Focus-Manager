# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Install dependencies required for JavaFX (for GUI support)
RUN apt-get update && apt-get install -y \
    libgl1-mesa-glx \
    libxi6 \
    libxrender1 \
    libxrandr2 \
    libasound2

# Copy the generated .jar file from the target directory to the container
COPY target/Focus_Manager-1.0-SNAPSHOT.jar focus.jar

# Set the default command to run your JavaFX app
ENTRYPOINT ["java", "-jar", "focus.jar"]

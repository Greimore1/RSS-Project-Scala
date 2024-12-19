# Use an official OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the sbt configuration and source files into the container
COPY backend/build.sbt /app/
COPY backend/project /app/project
COPY backend/src /app/src

# Install sbt
RUN echo "Updating package lists and installing curl..." && \
    apt-get update && apt-get install -y curl && \
    echo "Downloading and installing sbt..." && \
    curl -L https://github.com/sbt/sbt/releases/download/v1.9.6/sbt-1.9.6.tgz | tar xz -C /usr/local && \
    ln -s /usr/local/sbt/bin/sbt /usr/local/bin/sbt

# Cache dependencies by running sbt compile first
RUN echo "Starting sbt compile to cache dependencies..." && sbt compile

# Expose the application port (if applicable)
EXPOSE 8080

# Run the application
CMD ["sbt", "run"]
# Use a imagem base do OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Configura JAVA_HOME e adiciona ao PATH
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Define o diretório de trabalho
WORKDIR /app

# Copia o gradle wrapper e o diretório gradle
COPY gradlew ./
COPY gradle ./gradle

# Concede permissão para o gradlew
RUN chmod +x ./gradlew

# Copia o restante do código da aplicação para o contêiner
COPY . .

# Compila o projeto sem tentar resolver dependências de sistema
RUN ./gradlew build -x test --no-daemon --info

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]

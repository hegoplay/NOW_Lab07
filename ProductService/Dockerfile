# Sử dụng image Java cơ sở (JDK 17 làm ví dụ)
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build

# Thiết lập thư mục làm việc
WORKDIR /app

# Copy file pom.xml và source code
COPY pom.xml .
COPY src ./src

# Cài đặt Maven và build ứng dụng
RUN mvn clean package -DskipTests

# Tạo image chạy ứng dụng
FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose port ứng dụng (mặc định 8080)
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
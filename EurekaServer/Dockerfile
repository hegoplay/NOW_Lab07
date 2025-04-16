FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /app
COPY /target/*.jar app.jar

# Expose port ứng dụng (mặc định 8080)
EXPOSE 8761

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
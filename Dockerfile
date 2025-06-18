FROM openjdk:17-slim
EXPOSE 8080

ARG JAR_FILE
COPY ${JAR_FILE} app.jar

# 1. 환경 변수 먼저 선언
ENV TZ=Asia/Seoul

# 2. 시간대 설정을 환경변수 기준으로 심볼릭 링크
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 3. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
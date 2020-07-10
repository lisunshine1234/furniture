FROM java:8
MAINTAINER [liziyang] [178513111@qq.com]

WORKDIR /app
ADD furniture-1.0.1.jar /app
ENV MODEL prod
ENV CONTEXT_PATH /furniture
ENV MYSQL_URL localhost:3306
ENV DB_NAME furniture
ENV MYSQL_LOGIN root
ENV MYSQL_PASSWORD  123456
ENV REDIS_NODES localhost:26379,localhost:26380,localhost:26381
ENV REDIS_URL localhost
ENV REDIS_PORT 6379
ENV RESOURCE_PATH /furniture
ENV INDEX_CONUT 24

ENTRYPOINT ["java", "-jar", "/app/furniture-1.0.1.jar"]

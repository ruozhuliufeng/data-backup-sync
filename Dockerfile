FROM registry.cn-hangzhou.aliyuncs.com/ruozhuliufeng/open-jdk:8-jdk-alpine

EXPOSE 5525
ADD target/data-backup-0.0.1.jar app.jar

CMD java -jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
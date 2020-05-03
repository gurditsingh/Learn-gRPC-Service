FROM java:8
WORKDIR /
ADD artifact/Learn-gRPC-Service-1.0.jar Learn-gRPC-Service-1.0.jar
EXPOSE 1313
ENTRYPOINT ["java","-jar","Learn-gRPC-Service-1.0.jar"]
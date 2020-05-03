package learn.grpc.service;

import io.grpc.stub.StreamObserver;
import learn.grpc.proto.code.GreetingGrpc;
import learn.grpc.proto.code.GreetingOuterClass;

public class GreetingService extends GreetingGrpc.GreetingImplBase {

    @Override
    public void sayHello(GreetingOuterClass.APIRequest request,
                         StreamObserver<GreetingOuterClass.APIResponse> responseObserver) {

        System.out.println("say hello API call");

        String name=request.getName();
        GreetingOuterClass.APIResponse.Builder response = GreetingOuterClass.APIResponse.newBuilder();

        response.setResponsemessage("Hello : "+name+" : Welcome to GRPC World").setResponseCode(100);

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();

    }
}

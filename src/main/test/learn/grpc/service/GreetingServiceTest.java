package learn.grpc.service;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import learn.grpc.proto.code.GreetingGrpc;
import learn.grpc.proto.code.GreetingOuterClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreetingServiceTest {


    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @Test
    public void greetingReplyMessage() throws Exception {
        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();

        // Create a server, add service, start, and register for automatic graceful shutdown.
        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(new GreetingService()).build().start());

        GreetingGrpc.GreetingBlockingStub blockingStub = GreetingGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

        GreetingOuterClass.APIRequest request=GreetingOuterClass.APIRequest.newBuilder().setName("Gurdit Singh").build();

        GreetingOuterClass.APIResponse response = blockingStub.sayHello(request);

        assertEquals("Hello : Gurdit Singh : Welcome to GRPC World", response.getResponsemessage());
    }
}


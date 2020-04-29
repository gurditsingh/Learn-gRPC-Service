package learn.grpc.proto.code.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import learn.grpc.proto.code.service.GreetingService;

import java.io.File;

public class GreetingServer {

    private Server server;

    public static void main(String[] args) {

        try {
            GreetingServer greetingServer=new GreetingServer();
            greetingServer.start();
        }
        catch (Exception e){
            System.err.print(e);
        }
    }


    private void start() throws Exception {
        final int port = 9000;

        server=ServerBuilder.forPort(port)
                .addService(new GreetingService())
                .build()
                .start();

        System.out.println("GRPC server started at port : "+ port);

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("Shutdown the server");
                GreetingServer.this.stop();
            }
        });

        server.awaitTermination();
    }

    private void stop(){
        if(server !=null)
            server.shutdown();
    }
}

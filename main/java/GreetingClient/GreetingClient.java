package GreetingClient;

import com.proto.dummy.Dummy;
import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50055)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");
//        DummyServiceGrpc.DummyServiceBlockingStub syncClient
//                = DummyServiceGrpc.newBlockingStub(channel);

//        DummyServiceGrpc.DummyServiceFutureStub asyncClient
//                = DummyServiceGrpc.newFutureStub(channel);

        // create a greet service client (blocking - synchronus)
        GreetServiceGrpc.GreetServiceBlockingStub greetClient;
        greetClient = GreetServiceGrpc.newBlockingStub(channel);

        //created a protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder()
                        .setFirstName("John")
                        .setLastName("Baan")
                        .build();

        //create a protocol buffer greetRequest message
        GreetRequest greetRequest = GreetRequest.newBuilder()
                        .setGreeting(greeting)
                        .build();

        //call the RPC and back a GreetResponse (Protocol Buffers)
        GreetResponse greetResponse = greetClient.greet(greetRequest);

        //show the result in GreetResponse message
        System.out.println(greetResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}

package SumClient;

import com.proto.sum.Sum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {
    public static void main(String[] args) {
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 55554)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");
//        DummyServiceGrpc.DummyServiceBlockingStub syncClient
//                = DummyServiceGrpc.newBlockingStub(channel);

//        DummyServiceGrpc.DummyServiceFutureStub asyncClient
//                = DummyServiceGrpc.newFutureStub(channel);

        // create a greet service client (blocking - synchronus)
        SumServiceGrpc.SumServiceBlockingStub sumClient;
        sumClient = SumServiceGrpc.newBlockingStub(channel);

        //created a protocol buffer greeting message
        Sum summing = Sum.newBuilder()
                .setFirstNum("5")
                .setLastNum("10")
                .build();

        //create a protocol buffer greetRequest message
        SumRequest sumRequest = SumRequest.newBuilder()
                .setSum(summing)
                .build();

        //call the RPC and back a GreetResponse (Protocol Buffers)
        SumResponse sumResponse = sumClient.sum(sumRequest);

        //show the result in GreetResponse message
        System.out.println(sumResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}

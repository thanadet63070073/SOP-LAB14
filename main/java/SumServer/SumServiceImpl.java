package SumServer;

import com.proto.greet.Greeting;
import com.proto.sum.*;
import io.grpc.stub.StreamObserver;

import static java.lang.String.valueOf;

public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase{
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver){
        // Block 1: extract the data required
        Sum summing = request.getSum();
        int firstNum = Integer.parseInt(summing.getFirstNum());
        int lastNum = Integer.parseInt(summing.getLastNum());
        int num = firstNum+lastNum;
        System.out.println("Server Output : " + firstNum + " + " + lastNum + " = " + num);

        // Block 2: create the response message
        String result = valueOf(num);
        SumResponse response = SumResponse.newBuilder()
                .setResult(result)
                .build();

        // Block 3: send the response
        responseObserver.onNext(response);

        // Block 4: complete the RPC call
        responseObserver.onCompleted();
    }
}

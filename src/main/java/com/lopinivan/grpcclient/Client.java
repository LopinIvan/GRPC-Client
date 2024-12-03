package com.lopinivan.grpcclient;

import com.lopinivan.grpcserver.GreetingServiceGrpc;
import com.lopinivan.grpcserver.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest.newBuilder()
                .setName("Lopin Ivan")
                .build();

        Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);

        int i = 0;
        while (response.hasNext()) {
            System.out.println(response.next() + " " + i);
            i++;
        }

        channel.shutdownNow();
    }
}

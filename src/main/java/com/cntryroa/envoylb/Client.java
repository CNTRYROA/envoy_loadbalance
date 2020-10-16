package com.cntryroa.envoylb;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.justtest.GreeterGrpc;
import io.grpc.justtest.TestRequest;
import io.grpc.justtest.TestResponse;

import java.util.concurrent.TimeUnit;

public class Client {
    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;
    private static final String host = "127.0.0.1";
    private static final int ip = 35001;

    public Client(String host, int port) {
        //usePlaintext表示明文传输，否则需要配置ssl
        //channel  表示通信通道
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        //存根
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void testResult(String name) {
        TestRequest request = TestRequest.newBuilder().setName(name).build();
        TestResponse response = blockingStub.testSomeThing(request);
        System.out.println(response.getMessage());
    }

    public static void main(String[] args) {
        Client client = new Client(host, ip);
        for (int i = 0; i <= 5; i++) {
            client.testResult("<<<<<result>>>>>:" + i);
        }
    }
}

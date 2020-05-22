package com.rx.reactive.subscribers;

public class        SubscriberNumberOperations {

    public static void duplicate (Integer n){
        // Simulamos que este proceso tarda 5 segundos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int  result = n*2;
        System.out.println("Subscriber NumberOperations duplicate number, result: "  + result);
    }

    public static void plusOne (Integer n){
        int result = n+1;
        System.out.println("Subscriber NumberOperations sums 1, result: "  + result);
    }

}

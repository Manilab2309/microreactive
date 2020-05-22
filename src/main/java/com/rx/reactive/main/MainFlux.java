package com.rx.reactive.main;

import reactor.core.publisher.Flux;

public class MainFlux {

    public static void main(String[] args){
    Flux<Integer> fluxNumbers = Flux.range(5,100);

    fluxNumbers.subscribe(x -> System.out.println(x + " "));

    }

}

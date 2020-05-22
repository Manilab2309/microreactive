package com.rx.reactive.web;

import com.rx.reactive.entity.Stage;
import com.rx.reactive.service.SimpleNumbersService;
import com.rx.reactive.service.StageService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import com.rx.reactive.subscribers.SubscriberNumberOperations;

import java.time.Duration;

@RestController
@RequestMapping(path = "/api")

@Timed
public class PublisherController {

    @Autowired
    private SimpleNumbersService numbersService;

    @Autowired
    private MeterRegistry meterRegistry;

    @GetMapping("/hello")
    public String health() {
        return "Im the controller! Hello";
    }

    @GetMapping(path = "/numeros", produces= "text/event-stream")
    public Flux<Integer> numbers(){

        Flux<Integer> flux = Flux.range(1,100)
                .delayElements(Duration.ofSeconds(1))
                .doOnEach(i -> numbersService.checkNumber(i.get()));

        //flux.log();

        // Añadimos a los oyentes
        //flux.subscribe(System.out::println);
        //flux.subscribe(SubscriberNumberOperations::duplicate);
        //flux.subscribe(SubscriberNumberOperations::plusOne);

        return flux;
    }

    @GetMapping(path = "/filtered", produces= "text/event-stream")
    public Flux<Integer> filtered(){

        // Del stream completo nos quedamos con los pares y a estos los multiplicamos por 2
        Flux<Integer> flux = Flux.range(1,10)
                .delayElements(Duration.ofSeconds(3))
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2);

        // Añadimos a los oyentes
        flux.subscribe(System.out::println);
        flux.subscribe(SubscriberNumberOperations::duplicate);
        flux.subscribe(SubscriberNumberOperations::plusOne);

        return flux;
    }


}

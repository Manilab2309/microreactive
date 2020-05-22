package com.rx.reactive.service;

import com.rx.reactive.entity.Stage;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class SimpleNumbersService {

    private List<Stage> stages;


    private Counter pairNumberCounter;

    private Counter oddNumberCounter;

    public SimpleNumbersService(MeterRegistry meterRegistry) {

        // Counters setup initialization
        pairNumberCounter = meterRegistry.counter("numbers.pairs"); // 1 - create a counter
        oddNumberCounter = meterRegistry.counter("numbers.odds"); // 1 - create a counter

        // Con close () provocas el reinicio de los contadores al reiniciar el micro
        pairNumberCounter.close();
        oddNumberCounter.close();
    }

    public void checkNumber(int number) {

        System.out.println("Número a examinar - " + String.valueOf(number));

        if (number % 2 == 0) {
            pairNumberCounter.increment();  // 3 - increment the counter
            System.out.println("Incremento contador PAIR- " + pairNumberCounter.count());
        } else {
            oddNumberCounter.increment();
            System.out.println("Incremento contador ODD - " + oddNumberCounter.count());

        }
    }
}

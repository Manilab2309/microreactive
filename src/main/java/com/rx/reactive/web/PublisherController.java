package com.rx.reactive.web;

import com.rx.reactive.entity.Stage;
import com.rx.reactive.service.SimpleNumbersService;
import com.rx.reactive.service.StageService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import com.rx.reactive.subscribers.SubscriberNumberOperations;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
@Timed
public class PublisherController {

    @Autowired
    private SimpleNumbersService numbersService;

    @Autowired
    private StageService stageService;

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private Stage stage = new Stage();

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

    @RequestMapping(path = "/updateSalary", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateSalary(@RequestBody  Map<String, Object> payload){

        // LLamada de postman que pide actualizar la nomina, ejemplo
        //{
        //    "newSalary": "2000"
        //}
        JSONObject jsonObject = new JSONObject(payload);
        try {
            stageService.updateSalary(Integer.parseInt((String)jsonObject.get("newSalary")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "{\"response\":\"SALARY UPDATED\"}";
    }

    @RequestMapping(path = "/updateExpenses", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateExpenses(@RequestBody  Map<String, Object> payload){

        // LLamada de postman que pide actualizar la nomina, ejemplo
        //{
        //    "newExpenses": "500"
        //}

        JSONObject jsonObject = new JSONObject(payload);
        try {
            stageService.updateExpenses(Integer.parseInt((String)jsonObject.get("newExpenses")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "{\"response\":\"EXPENSES UPDATED\"}";
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

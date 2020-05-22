package com.rx.reactive.service;

import com.rx.reactive.entity.Stage;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StageService {

    private MeterRegistry meterRegistry;
    private List<Stage> stages;
    private Counter stageOrderCounter;
    Counter.Builder aleOrderCounter;

    public StageService(MeterRegistry meterRegistry) {

        this.meterRegistry = meterRegistry;

    }

    private void initStageCounters() {
        stageOrderCounter = this.meterRegistry.counter("stage", "salary", "expenses"); // 1 - create a counter
        aleOrderCounter = (Counter.Builder) Counter.builder("stage")    // 2 - create a counter using the fluent API
                .tag("salary", "1000")
                .description("Home stage of live cost")
                .register(meterRegistry);
    }

    public void orderStage(Stage stage) {

        stages.add(stage);
        stageOrderCounter.increment(1.0);  // 3 - increment the counter

    }
}

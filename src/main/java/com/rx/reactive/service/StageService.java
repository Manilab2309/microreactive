package com.rx.reactive.service;

import com.rx.reactive.entity.Stage;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StageService {

    private MeterRegistry meterRegistry;
    private AtomicInteger salaryGauge;
    private AtomicInteger expensesGauge;
    private AtomicInteger profitGauge;

    @Autowired
    private Stage stage;

    public StageService(MeterRegistry meterRegistry, Stage stage) {
        this.meterRegistry = meterRegistry;

        // Salary meter
        salaryGauge = meterRegistry.gauge("microreactive.salary", new AtomicInteger(stage.getSalary()));

        // Expenses meter
        expensesGauge = meterRegistry.gauge("microreactive.expenses", new AtomicInteger(stage.getExpenses()));

        // Profit
        profitGauge = meterRegistry.gauge("microreactive.profit", new AtomicInteger(stage.getProfit()));
    }

    public void updateSalary(int newSalary){

        stage.setSalary(newSalary);
        salaryGauge.set(newSalary);
        System.out.println("Nuevo salario:  "+String.valueOf(newSalary));

        updateProfit();


    }

    public void updateExpenses(int newExpenses){

        stage.setExpenses(newExpenses);
        expensesGauge.set(newExpenses);
        System.out.println("Nuevo gasto:  "+String.valueOf(newExpenses));

        updateProfit();
    }

    private void updateProfit() {

        int profit = stage.getSalary() - stage.getExpenses();
        stage.setProfit(profit);
        profitGauge.set(profit);

        System.out.println("Nuevo ahorro  "+String.valueOf(profit));

    }
}

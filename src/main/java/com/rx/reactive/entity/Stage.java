package com.rx.reactive.entity;

/*
    Simula un escenario de salario y gasto en un momento determinado
 */

import org.springframework.stereotype.Component;

@Component
public class Stage {

    int salary;
    int expenses;
    int profit;

    // Default Constructor
    public Stage(){
        this.salary = 1000;
        this.expenses = 100;
        this.profit = 900;
    }

    // Constructor
    public Stage(int salary, int expenses){
        this.salary = salary;
        this.expenses = expenses;
        this.profit = salary - expenses;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getProfit() {return profit;}

    public void setProfit(int profit) {this.profit = profit;}

}

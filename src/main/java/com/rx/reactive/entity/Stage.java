package com.rx.reactive.entity;

/*
    Simula un escenario de salario y gasto en un momento determinado
 */
public class Stage {

    int salary;
    int expenses;

    // Constructor
    public Stage(int salary, int expenses){
        this.salary = salary;
        this.expenses = expenses;
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

}

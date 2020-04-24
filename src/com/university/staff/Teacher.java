package com.university.staff;

public abstract class Teacher{
    private int identification;
    private String name;
    private double baseSalary;

    public Teacher(int identification, String name, double baseSalary) {
        this.identification = identification;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double calculateSalary();

}

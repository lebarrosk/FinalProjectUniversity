package com.university.staff;

public abstract class Teacher{
    private int id;
    private String name;
    private double baseSalary;

    public Teacher(int id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString(){
    return " Id: " + id +
            "; Name: " + name +
            "; Salary: " + calculateSalary();
    }
}

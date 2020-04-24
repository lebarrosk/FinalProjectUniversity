package com.university.staff;

public class PartTimeTeacher extends Teacher{
    private int activeHoursPerWeek;

    public PartTimeTeacher(int identification, String name, double baseSalary) {
        super(identification, name, baseSalary);
    }

    public PartTimeTeacher(int identification, String name, double baseSalary, int activeHoursPerWeek) {
        super(identification, name, baseSalary);
        this.activeHoursPerWeek = activeHoursPerWeek;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() * activeHoursPerWeek;
    }
}

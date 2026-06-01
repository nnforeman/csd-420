/*
Name: Natasha Foreman
Course: CSD 420 - Advanced Java
Date: May 31st, 2026
Assignment: Module 11
Purpose: Example of Java JSON.
*/

import com.google.gson.Gson;
import java.util.ArrayList;

class Employee {
    String name;
    int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
}

public class ForemanAssignment_11 {

    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee("John", 101));
        employees.add(new Employee("Mary", 102));
        employees.add(new Employee("David", 103));

        Gson gson = new Gson();

        String json = gson.toJson(employees);

        System.out.println(json);
    }
}
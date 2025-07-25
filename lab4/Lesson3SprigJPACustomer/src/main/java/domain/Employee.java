package domain;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    private String employeeNumber;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
    }

    public Employee(String employeeNumber, String name, Department department) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.department = department;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

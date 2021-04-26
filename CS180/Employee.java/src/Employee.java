import java.util.Random;

public class Employee {
    private int idNumber;
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position) {
        this.name = name;
        if (name == null) {
            this.name = "";
        }
        this.position = position;
        salary = 25000;
        if (position == null) {
            this.position = "";
        } else if (position.equals("Manager")) {
            salary = 50000.00;
        } else {
            salary = 25000.00;
        }
        idNumber = generateIdNumber();
    }

    private int generateIdNumber() {
        Random r = new Random();
        return r.nextInt(900000) + 100000;
    }

    public int getIdNumber() {
        return this.idNumber;
    }

    public String getName() {
        return this.name;

    }

    public String getPosition() {
        return this.position;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
        if (position == null) {
            this.position = "";
        } else if (position.equals("Manager")) {
            salary = 50000;
        } else {
            salary = 25000;
        }
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            this.salary = 0;
        } else {
            this.salary = salary;
        }
    }

    public String toString() {
        return String.format("ID number: %d \nName: %s \nPosition: %s \nSalary: $%.2f",
                getIdNumber(), getName(), getPosition(), getSalary());
    }

    public static void main(String[] args) {
        Employee emp = new Employee("Jack", "employee");
        emp.setPosition("Manager");
        System.out.println(emp.toString());
    }
}

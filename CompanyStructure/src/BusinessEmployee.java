public class BusinessEmployee extends Employee {

    private static double DEFAULT_BASE_SALARY = 50000;

    public BusinessEmployee(String name) {
        super(name, DEFAULT_BASE_SALARY);
    }

    public BusinessEmployee(String name, double baseSalary) {
        super(name, baseSalary);
    }

    public double getBonusBudget() {
        return 0;
    }

    public String employeeStatus() {
        return ID + " " + name + " with a budget of " + getBonusBudget();
    }

}


// done

public class BusinessEmployee extends Employee {

    protected static double DEFAULT_BASE_SALARY = 50000;
    protected double bonusBudget;


    public BusinessEmployee(String name) {
        super(name, DEFAULT_BASE_SALARY);
    }

    public BusinessEmployee(String name, double baseSalary) {
        super(name, baseSalary);
    }

    public double getBonusBudget() {

        return this.bonusBudget;
    }

    public String employeeStatus() {
        return ID + " " + name + " with a budget of " + this.bonusBudget;
    }

}


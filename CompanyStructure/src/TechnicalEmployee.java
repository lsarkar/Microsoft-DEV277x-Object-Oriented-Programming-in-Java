// done

public class TechnicalEmployee extends Employee {

    protected static double DEFAULT_BASE_SALARY = 75000;
    protected int successfulCheckinCount = 0;

    public TechnicalEmployee(String name) {
        super(name, DEFAULT_BASE_SALARY);
    }

    public TechnicalEmployee(String name, double baseSalary) {
        super(name, baseSalary);
    }

    public void addCheckin() {
        successfulCheckinCount++;
    }

    public String employeeStatus() {
       return ID + " " + name + " has " + successfulCheckinCount + " successful checkins";
    }

}

public abstract class Employee {

    protected String name;
    protected double baseSalary;
    protected Employee manager;
    protected String status;
    protected static int ID = 0;

    public Employee(String name, double baseSalary) {
      this.name = name;
      this.baseSalary = baseSalary;
      ID++;
    }

    public double getBaseSalary() {
        return this.baseSalary;
    }

    public String getName() {
        return this.name;
    }

    public int getEmployeeID() {
        return ID;
    }

    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public boolean equals(Employee other) {
        if (ID == other.getEmployeeID()) {
            return true;
        }
        else
            return false;
    }

    public String toString() {
        return ID + " " + this.name;
    }

    public abstract String employeeStatus();

}

public class Accountant extends Employee implements IBonusApproval {

    protected TechnicalLead supportedLead;
    protected BusinessLead manager;

    protected static double PERCENT_SALARY_INC = 1.1;
    protected double bonusBudget;

    public Accountant(String name) {
        //TODO: modify base salary
        super(name, 0);
        this.bonusBudget = 0;
    }

    public TechnicalLead getTeamSupported() {
        return this.supportedLead;
    }

    public void supportTeam(TechnicalLead lead){
        this.supportedLead = lead;

        // TODO calculate base salary based on SW engineers
        for (Employee e : this.supportedLead.getDirectReports()) {
            this.baseSalary += e.getBaseSalary();
        }

        this.baseSalary *= PERCENT_SALARY_INC;

    }

    private void calculateSalary() {

    }

    public boolean approveBonus(Employee e, double bonus) {
        return false;
    }

    public String employeeStatus() {
        return null;
    }

}



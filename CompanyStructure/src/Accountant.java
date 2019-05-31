// done

public class Accountant extends BusinessEmployee {

    protected TechnicalLead supportedLead = null;
    protected BusinessLead manager;

    protected static double PERCENT_BONUS_BUDGET = 1.1;

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

        for (Employee e : this.supportedLead.getDirectReports()) {
            this.bonusBudget += e.getBaseSalary();
        }

        this.bonusBudget *= PERCENT_BONUS_BUDGET;

    }

    public boolean approveBonus(double bonus) {
        if (this.supportedLead == null) {
            return false;
        }

        return bonus < this.bonusBudget;
    }

    public String employeeStatus() {
        return this.ID + " " + this.name + "with a budget of " + this.bonusBudget + " is supporting " + this.supportedLead.getName();
    }

    public Employee getEmployee() {
        return this.manager;
    }

}



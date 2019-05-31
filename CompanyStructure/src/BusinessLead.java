import java.util.*;

public class BusinessLead extends BusinessEmployee implements IBonusApproval, IBonusRequest, IManager {

    protected static int DEFAULT_HEADCOUNT = 10;
    protected List<TechnicalLead> supportTeam = new ArrayList<TechnicalLead>();
    protected List<Accountant> accountants = new ArrayList<Accountant>();
    protected static double REPORT_ADD_BONUS = 1.1;


    public BusinessLead(String name) {
        super(name);
    }

    public boolean approveBonus(Employee e, double bonus) {
        return false;
    }

    public void addReport(Accountant e, TechnicalLead supportTeam) {
        this.accountants.add(e);
        this.supportTeam.add(supportTeam);

        this.baseSalary *= REPORT_ADD_BONUS;
    }

    public boolean requestBonus(Employee e, double bonus) {

        if(budget>=bonus) {
            e.bonus += bonus;
            budget-=bonus;
            return true;
        }
        return false;
    }

    public List<Accountant> getDirectReports() {
        return this.accountants;
    }

    public int getDirectReportSize() {
        return this.accountants.size() + this.supportTeam.size();
    }

    public boolean hasHeadCount() {
        return getDirectReportSize() >= DEFAULT_HEADCOUNT;
    }

    public String getTeamStatus() {
        return null;
    }

}

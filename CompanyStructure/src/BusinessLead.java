import java.util.*;

public class BusinessLead extends BusinessEmployee implements IBonusApproval, IBonusRequest, IManager {

    protected static int DEFAULT_HEADCOUNT = 10;
    protected List<TechnicalLead> supportTeam = new ArrayList<TechnicalLead>();
    protected List<Accountant> accountant = new ArrayList<Accountant>();
    protected static double REPORT_ADD_BONUS = 1.1;


    public BusinessLead(String name) {
        super(name);
    }

    public boolean approveBonus(Employee e, double bonus) {
        return false;
    }

    public void addReport(Accountant e, TechnicalLead supportTeam) {
        this.accountant.add(e);
        this.supportTeam.add(supportTeam);

        this.baseSalary *= REPORT_ADD_BONUS;
    }

    public boolean requestBonus(Employee e, double bonus) {
        return false;
    }

    public int getDirectReports() {
        return this.accountant.size() + this.supportTeam.size();
    }

    public boolean hasHeadCount() {
        return getDirectReports() >= DEFAULT_HEADCOUNT;
    }

    public String getTeamStatus() {
        return null;
    }

}

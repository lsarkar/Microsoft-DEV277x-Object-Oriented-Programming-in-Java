import java.util.*;

public class TechnicalLead extends TechnicalEmployee implements IBonusRequest, IManager {

    protected static int DEFAULT_HEADCOUNT = 4;
    protected int headCount;
    protected List<Employee> directReports = new ArrayList<Employee>();

    public TechnicalLead(String name) {
        super(name, DEFAULT_BASE_SALARY*1.3);
        this.headCount = 0;
    }

    public int getDirectReportSize() {
        return this.directReports.size();
    }

    public List<Employee> getDirectReports() {
        return this.directReports;
    }

    public boolean hasHeadCount() {
        return getDirectReportSize() >= DEFAULT_HEADCOUNT;
    }

    public boolean addReport(SoftwareEngineer e) {

        if (!hasHeadCount()) {
            e.setManager(this);
            this.directReports.add(e);
            this.headCount++;
            return true;
        }
        else {
            return false;
        }

    }

    public boolean approveCheckin(SoftwareEngineer e) {

        if (e.getManager() == this && e.getCodeAccess()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean requestBonus(Employee e, double bonus) {
        return false;
    }

    public String getTeamStatus() {
        return null;
    }
}

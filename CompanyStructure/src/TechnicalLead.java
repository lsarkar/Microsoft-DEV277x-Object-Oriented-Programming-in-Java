import java.util.*;

public class TechnicalLead extends TechnicalEmployee implements IBonusRequest, IManager {

    protected static int DEFAULT_HEADCOUNT = 4;
    protected int headCount;
    protected List<Employee> directReports = new ArrayList<Employee>();
    private BusinessLead manager;

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

    public Employee getManager() {
        return this.manager;
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
        //Should check if the bonus amount requested would be approved by the BusinessLead supporting this TechnicalLead. If it is, that employee should get that bonus and true should be returned. False should be returned otherwise

        if (this.manager.requestBonus(this, bonus)) {
            return true;
        }

        return false;
    }

    public String getTeamStatus() {
        //Should return a String that gives insight into this Manager and all their direct reports.
        // It should return a string that is a combination of the TechnicalLead's employee status
        // followed by each of their direct employee's status on subsequent lines.
        // If the TechnicalLead has no reports it should print their employee status followed by the text
        // " and no direct reports yet ". Example: "10 Kasey has 5 successful check ins and no direct reports yet".
        // If the TechnicalLead does have reports it might look something like "10 Kasey has 5 successful check ins and is managing: /n 5 Niky has 2 successful check ins"

        StringBuilder sb = new StringBuilder();

        sb.append(this.employeeStatus());

        if (this.directReports == null) {
            sb.append("and has no direct reports yet");
        }
        else {

            sb.append("And is managing:\n");
            for (final Employee e : this.directReports) {
                final TechnicalEmployee tE = (TechnicalEmployee) e;
                sb.append(tE.employeeStatus());
                sb.append("\n");
            }
        }

        return sb.toString();
    }

}

import java.util.HashMap;

public abstract class AbstractManager extends Employee implements IBonusRequest {

    protected HashMap<String, Employee> directReports = new HashMap<String, Employee>();
    protected int headCount = 0;
    protected int defaultHeadCount;

    public AbstractManager(String name, double baseSalary) {
        super(name, baseSalary);
    }

    public int getDirectReports() {
        return directReports.size();
    }

    protected void setDefaultHeadCount(int headCount) {
        this.defaultHeadCount = headCount;
    }

    protected boolean hasHeadCount() {
        return this.headCount >= 4;
    }

}

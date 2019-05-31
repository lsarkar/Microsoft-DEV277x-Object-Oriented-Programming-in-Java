// done

public class SoftwareEngineer extends TechnicalEmployee {

    private boolean hasCodeAccess;
    private TechnicalLead manager;

    public SoftwareEngineer(String name) {
        super(name);
        this.hasCodeAccess = false;
        this.successfulCheckinCount = 0;
    }

    public boolean getCodeAccess() {
        return this.hasCodeAccess;
    }

    public void setCodeAccess(boolean access) {
        this.hasCodeAccess = access;
    }

    public int getSuccessfulCheckins() {
        return this.successfulCheckinCount;
    }

    public boolean checkInCode() {

        if (this.manager.approveCheckin(this)) {
            this.successfulCheckinCount++;
            return true;
        }
        else {
            return false;
        }
    }

    public Employee getManager() {
        return this.manager;
    }

}

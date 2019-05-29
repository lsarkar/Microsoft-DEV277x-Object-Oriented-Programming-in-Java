public class SoftwareEngineer extends TechnicalEmployee {

    private boolean hasCodeAccess;

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

    public boolean checkInCode(boolean managerApproval) {

        if (managerApproval) {
            this.successfulCheckinCount++;
            return true;
        }
        else {
            return false;
        }
    }

}

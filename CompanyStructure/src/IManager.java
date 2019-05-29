import java.util.*;

public interface IManager {

    int getDirectReportSize();

    List<Employee> getDirectReports();

    boolean hasHeadCount();

    String getTeamStatus();
}

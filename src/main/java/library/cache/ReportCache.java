package library.cache;

import library.model.entity.Report;
import java.util.HashMap;
import java.util.Map;

public class ReportCache extends CacheGeneric<Long, Report> {
    private static Map cache = new HashMap();
    @Override
    public String getName() {
        return "reportCache";
    }
}

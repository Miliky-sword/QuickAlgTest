package common;

import java.util.ArrayList;
import java.util.List;

public class DataMgr {
    private static DataMgr instance = null;

    private List<Object> cases;

    private DataMgr() {
        cases = new ArrayList<>();
    }

    public static DataMgr newInstance() {
        if (null == instance) {
            instance = new DataMgr();
        }
        return instance;
    }
}

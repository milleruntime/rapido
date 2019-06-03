package bean;

import java.util.Map;

import config.RapidoConfig;

public class Tables {

    private final RapidoConfig c;
    private Map<String,String> tableNameToIdMap;

    public Tables(RapidoConfig config) {
        c = config;
    }

    public Map<String,String> getTables() {
        return tableNameToIdMap;
    }

    public void set(Map<String,String> tableNameToIdMap) {
        this.tableNameToIdMap = tableNameToIdMap;
    }
}

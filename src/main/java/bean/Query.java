package bean;

import java.util.Map;

import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Value;

public class Query {

  private final String startRow;
  private final String tableName;
  private final int count;
  private final Map<Key, Value> results;


  public Query(Map<Key, Value> results, String tableName, String startRow, int count) {
    this.results = results;
    this.startRow = startRow;
    this.tableName = tableName;
    this.count = count;
  }

  public String getStartRow() {
    return startRow;
  }

  public String getTableName() {
    return tableName;
  }

  public int getCount() { return count; }

  public Map<Key, Value> getResults() {
    return results;
  }
}

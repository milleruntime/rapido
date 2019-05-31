package bean;

public class Query {

  private final String row;
  private final String tableName;
  private final int count;

  public Query(String tableName, String row, int count) {
    this.row = row;
    this.tableName = tableName;
    this.count = count;
  }

  public String getRow() {
    return row;
  }

  public String getTableName() {
    return tableName;
  }

  public int getCount() { return count; }
}

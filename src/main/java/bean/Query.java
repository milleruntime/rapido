package bean;

public class Query {

  private final String userName;
  private final String password;
  private final String row;
  private final String tableName;

  public Query(String userName, String password, String row, String tableName) {
    this.userName = userName;
    this.password = password;
    this.row = row;
    this.tableName = tableName;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public String getRow() {
    return row;
  }

  public String getTableName() {
    return tableName;
  }
}

package control;

public enum Destination {
  CARS("/templates/cars.ftl"),
  HOME("/templates/index.ftl");

  private String path;
  Destination(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}

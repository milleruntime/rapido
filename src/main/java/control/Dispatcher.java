package control;

import static control.Destination.HOME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Car;

/**
 * Class that forwards requests to the proper Freemarker template.
 */
public class Dispatcher {
  private HttpServletRequest request;
  private HttpServletResponse response;
  private Destination destination;

  public Dispatcher(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
    this.destination = HOME;
  }

  /**
   * Dispatch the request to the template.
   *
   * @throws ServletException
   * @throws IOException
   */
  public void go() throws ServletException, IOException {
    request.getRequestDispatcher(destination.getPath()).forward(request, response);
  }

  public void setDestination(Destination destination) {
    this.destination = destination;
  }

  public void getCars(HttpServletRequest request) {
    Car c1 = new Car("Audi", 52642);
    Car c2 = new Car("Volvo", 29000);
    Car c3 = new Car("Skoda", 9000);

    List<Car> cars = new ArrayList<>();
    cars.add(c1);
    cars.add(c2);
    cars.add(c3);

    request.setAttribute("cars", cars);
  }
}

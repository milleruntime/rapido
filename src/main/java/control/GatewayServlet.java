package control;

import static control.Destination.CARS;
import static control.Destination.HOME;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GatewayServlet", urlPatterns = {"/"})
public class GatewayServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    Dispatcher dispatcher = new Dispatcher(request, response);

    String carsParameter = request.getParameter("cars");
    System.out.println("CARS = " + carsParameter);

    if (carsParameter == null) {
      dispatcher.setDestination(HOME);
      request.setAttribute("title", "Welcome Page");
      request.setAttribute("greeting", "Put another shrimp on the barbie!");
    } else {
      dispatcher.setDestination(CARS);
      dispatcher.getCars(request);
    }

    dispatcher.go();
  }


}
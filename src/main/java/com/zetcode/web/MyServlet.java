package com.zetcode.web;

import com.zetcode.bean.Car;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyServlet", urlPatterns = {"/"})
public class MyServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String fwdPath = "/cars.ftl";

    response.setContentType("text/html;charset=UTF-8");

    String carsParameter = request.getParameter("cars");
    System.out.println("CARS = " + carsParameter);

    if (carsParameter == null) {
      fwdPath = "/home.ftl";
      request.setAttribute("title", "Welcome Page");
      request.setAttribute("greeting", "Put another shrimp on the barbie!");
    } else {
      getCars(request);
    }

    request.getRequestDispatcher(fwdPath).forward(request, response);
  }

  private void getCars(HttpServletRequest request) {
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
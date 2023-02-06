package ru.javawebinar.topjava.web;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

  private static final Logger log = getLogger(MealServlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    log.debug("redirect to meals");
    req.setAttribute("meals", MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.MIN,
        LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
    req.getRequestDispatcher("/meals.jsp").forward(req, resp);
  }
}

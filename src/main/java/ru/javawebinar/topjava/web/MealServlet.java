package ru.javawebinar.topjava.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MemoryStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

  private static final Logger log = getLogger(MealServlet.class);
  private Storage storage;

  @Override
  public void init() {
    storage = new MemoryStorage();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    req.setCharacterEncoding("UTF-8");
    String id = req.getParameter("id");

    Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id)
        , LocalDateTime.parse(req.getParameter("dateTime"))
        , req.getParameter("description")
        , Integer.parseInt(req.getParameter("calories")));

    log.info(meal.isNew() ? "Create meal" : "Update meal");
    storage.save(meal);
    resp.sendRedirect("meals");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");

    switch (action == null ? "all" : action) {
      case "delete":
        int id = getId(req);
        log.info("delete id = " + id);
        storage.delete(id);
        resp.sendRedirect("meals");
        break;
      case "create":
      case "update":
        final Meal meal = action.equals("create") ? new Meal(LocalDateTime.now().truncatedTo(
            ChronoUnit.MINUTES), "", 0) :
            storage.get(getId(req));
        req.setAttribute("meal", meal);
        req.getRequestDispatcher("/mealForm.jsp").forward(req, resp);
        break;
      case "all":
      default:
        log.info("getAll");
        req.setAttribute("meals", MealsUtil.filteredByStreams(storage.getAll(), LocalTime.MIN,
            LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        break;
    }
  }

  private int getId(HttpServletRequest req) {
    String paramId = Objects.requireNonNull(req.getParameter("id"));
    return Integer.parseInt(paramId);
  }
}

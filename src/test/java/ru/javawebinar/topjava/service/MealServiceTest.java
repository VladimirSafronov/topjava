package ru.javawebinar.topjava.service;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.MealTestData.getCreated;
import static ru.javawebinar.topjava.MealTestData.getUpdated;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@ContextConfiguration({
    "classpath:spring/spring-app.xml",
    "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

  static {
    SLF4JBridgeHandler.install();
  }

  @Autowired
  private MealService service;
  @Autowired
  private MealRepository repository;

  @Test
  public void get() {
    Meal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
    assertMatch(actual, ADMIN_MEAL_1);
  }

  @Test
  public void delete() {
    service.delete(MEAL1_ID, USER_ID);
    Assert.assertNull(repository.get(MEAL1_ID, USER_ID));
  }

  @Test
  public void deleteNotFound() {
    assertThrows(NotFoundException.class, () -> service.delete(1, USER_ID));
  }

  @Test
  public void deleteNotOwn() {
    assertThrows(NotFoundException.class, () -> service.delete(MEAL1_ID, ADMIN_ID));
  }

  @Test
  public void getAll() {
    assertMatch(service.getAll(USER_ID), MEALS);
  }

  @Test
  public void update() {
    Meal updated = getUpdated();
    service.update(updated, USER_ID);
    assertMatch(service.get(MEAL1_ID, USER_ID), updated);
  }

  @Test
  public void updateNotFound() {
    assertThrows(NotFoundException.class, () -> service.update(MEAL1, ADMIN_ID));
  }

  @Test
  public void create() {
    Meal newMeal = getCreated();
    Meal created = service.create(newMeal, USER_ID);
    Integer newId = created.getId();
    newMeal.setId(newId);
    assertMatch(created, newMeal);
  }
}
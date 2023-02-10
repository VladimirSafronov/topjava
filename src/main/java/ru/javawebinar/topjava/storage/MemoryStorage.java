package ru.javawebinar.topjava.storage;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import ru.javawebinar.topjava.model.Meal;

public class MemoryStorage implements Storage {

  private final AtomicInteger counter = new AtomicInteger(0);
  Map<Integer, Meal> storage = new ConcurrentHashMap<>();

  {
    save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
    save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
    save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
    save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение",
        100));
    save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
    save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
    save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
  }

  @Override
  public void delete(int id) {
    storage.remove(id);
  }

  @Override
  public Meal save(Meal meal) {
    if (meal.isNew()) {
      meal.setId(counter.incrementAndGet());
    }
    return storage.put(meal.getId(), meal);
  }

  @Override
  public Meal get(int id) {
    return storage.get(id);
  }

  @Override
  public Collection<Meal> getAll() {
    return storage.values();
  }
}

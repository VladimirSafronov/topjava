package ru.javawebinar.topjava.storage;

import java.util.Collection;
import java.util.List;
import ru.javawebinar.topjava.model.Meal;

public interface Storage {

  void delete(int id);

  Meal save(Meal meal);

  Meal get(int id);

  Collection<Meal> getAll();
}

package ru.javawebinar.topjava.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<Meal> getAll() {
        return new ArrayList<>(repository.getAll(SecurityUtil.authUserId()));
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id, SecurityUtil.authUserId()), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id, SecurityUtil.authUserId()), id);
    }

    public Meal create(Meal meal) {
        return repository.save(meal, SecurityUtil.authUserId());
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal, SecurityUtil.authUserId()), meal.getId());
    }
}
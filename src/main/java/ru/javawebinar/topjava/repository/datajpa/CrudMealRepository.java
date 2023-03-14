package ru.javawebinar.topjava.repository.datajpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
  @Transactional
  @Modifying
  @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
  int delete(@Param("id") int id, @Param("userId") int userId);

  @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
  List<Meal> getAll(@Param("userId") int userId);

  @Query("""
      SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime >= :startDate 
      AND m.dateTime < :endDate ORDER BY m.dateTime DESC
      """)
  List<Meal> getBetweenHalfOpen(@Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
}

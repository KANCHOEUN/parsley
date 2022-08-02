package com.ssafy.db.repository;

import com.ssafy.db.entity.DailyGoal;
import com.ssafy.db.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Repository
public class DailyGoalRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(DailyGoal dailyGoal){
        em.persist(dailyGoal);
    }

//    public DailyGoal findByUserId(Long userId){
//        return em.find(DailyGoal.class, userId);
//    }

    public DailyGoal findByUser(User user){
        return em.createQuery("select g from DailyGoal g where g.user = :user and g.date = :today", DailyGoal.class)
                .setParameter("user", user)
                .setParameter("today", LocalDate.now()).getResultList().get(0);
    }
}

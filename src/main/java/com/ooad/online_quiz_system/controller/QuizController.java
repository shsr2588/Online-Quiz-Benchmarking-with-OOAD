package com.ooad.online_quiz_system.controller;

import com.ooad.online_quiz_system.manager.QuizManager;
import com.ooad.online_quiz_system.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class QuizController {

    @GetMapping("/submit-quiz")
    public DeferredResult<String> submitQuiz(@RequestParam String username) {
        DeferredResult<String> deferredResult = new DeferredResult<>();


        int startHour = 21;
        int startMinute = 7;
        int endHour = 22;
        int endMinute = 30;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
        QuizManager quizManager = QuizManager.getInstance();
        User user = new User(username);
        quizManager.registerObserver(user);


        scheduler.scheduleAtFixedRate(() -> {
            LocalDateTime now = LocalDateTime.now();
            int currentHour = now.getHour();
            int currentMinute = now.getMinute();


            boolean isAfterStart = (currentHour > startHour) || (currentHour == startHour && currentMinute >= startMinute);
            boolean isBeforeEnd = (currentHour < endHour) || (currentHour == endHour && currentMinute <= endMinute);

            if (isAfterStart && isBeforeEnd) {
                quizManager.submitQuiz();
                deferredResult.setResult("The quiz has been submitted within the specified time range."); // Return response
            }


            if (!isBeforeEnd) {
                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.MINUTES); // Check every 1 minute

        return deferredResult;
    }
}
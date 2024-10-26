package com.ooad.online_quiz_system.manager;


import com.ooad.online_quiz_system.Observer;

import java.util.ArrayList;
import java.util.List;

public class QuizManager {

    private static volatile QuizManager instance;

    private List<Observer> observers = new ArrayList<>();

    private QuizManager() {}

    public static QuizManager getInstance() {
        if (instance == null) {
                    instance = new QuizManager();
        }
        return instance;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void submitQuiz() {
        notifyObservers();
    }
}
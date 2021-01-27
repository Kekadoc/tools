package com.qeapp.tools.timer;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public final class ObservableTimer extends Timer {

    public interface Observer {

        void onTimerCancel(ObservableTimer timer);
        void onTimerPurge(ObservableTimer timer);
        void onTimerTaskScheduled(ObservableTimer timer, TimerTask timerTask, long delay, long period);

        default void onTimerTaskPreRun(ObservableTimer timer, ObservableTimerTask timerTask) {

        }
        default void onTimerTaskPostRun(ObservableTimer timer, ObservableTimerTask timerTask) {

        }

    }

    private Collection<Observer> observers;
    private ObsTimerTaskObserver obsTimerTaskObserver;

    public ObservableTimer() {
    }
    public ObservableTimer(boolean isDaemon) {
        super(isDaemon);
    }
    public ObservableTimer(String name) {
        super(name);
    }
    public ObservableTimer(String name, boolean isDaemon) {
        super(name, isDaemon);
    }

    public void addObserver(Observer observer) {
        if (this.observers == null) this.observers = new LinkedHashSet<>();
        this.observers.add(Objects.requireNonNull(observer));
    }
    public void removeObserver(Observer observer) {
        if (this.observers == null) return;
        this.observers.remove(observer);
    }

    private ObsTimerTaskObserver getObsTimerTaskObserver() {
        if (obsTimerTaskObserver == null) obsTimerTaskObserver = new ObsTimerTaskObserver();
        return obsTimerTaskObserver;
    }

    @Override
    public void schedule(TimerTask task, long delay) {
        schedule(task, System.currentTimeMillis() + delay, 0);
    }
    @Override
    public void schedule(TimerTask task, Date time) {
        schedule(task, time.getTime(), 0);
    }
    @Override
    public void schedule(TimerTask task, long delay, long period) {
        if (task instanceof ObservableTimerTask) ((ObservableTimerTask) task).addObserver(getObsTimerTaskObserver());
        super.schedule(task, delay, period);
        if (observers != null) for (Observer observer : observers) observer.onTimerTaskScheduled(this, task, delay, period);
    }
    @Override
    public void schedule(TimerTask task, Date firstTime, long period) {
        schedule(task, firstTime.getTime(), -period);
    }
    @Override
    public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
        schedule(task, System.currentTimeMillis() + delay, period);
    }
    @Override
    public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) {
        schedule(task, firstTime.getTime(), period);
    }

    @Override
    public void cancel() {
        super.cancel();
        if (observers != null) for (Observer observer : observers) observer.onTimerCancel(this);
    }
    @Override
    public int purge() {
        int i = super.purge();
        if (observers != null) for (Observer observer : observers) observer.onTimerPurge(this);
        return i;
    }

    private final class ObsTimerTaskObserver implements ObservableTimerTask.TimerTaskObserver {
        @Override
        public void onTimerTaskPreRun(ObservableTimerTask task) {
            if (observers != null) for (Observer observer : observers) observer.onTimerTaskPreRun(ObservableTimer.this, task);
        }
        @Override
        public void onTimerTaskPostRun(ObservableTimerTask task) {
            if (observers != null) for (Observer observer : observers) observer.onTimerTaskPostRun(ObservableTimer.this, task);
        }
    }

}

package com.kekadoc.tools.timer;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.TimerTask;

public abstract class ObservableTimerTask extends TimerTask {

    public interface TimerTaskObserver {
        void onTimerTaskPreRun(ObservableTimerTask task);
        void onTimerTaskPostRun(ObservableTimerTask task);
    }

    private Collection<TimerTaskObserver> observers;

    public void addObserver(TimerTaskObserver observer) {
        if (this.observers == null) this.observers = new LinkedHashSet<>();
        this.observers.add(Objects.requireNonNull(observer));
    }
    public void removeObserver(TimerTaskObserver observer) {
        if (this.observers == null) return;
        this.observers.remove(observer);
    }

    protected abstract void action();

    @Override
    public final void run() {
        if (observers != null) for (TimerTaskObserver observer : observers) observer.onTimerTaskPreRun(this);
        action();
        if (observers != null) for (TimerTaskObserver observer : observers) observer.onTimerTaskPostRun(this);
    }

}

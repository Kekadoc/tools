package com.kekadoc.tools.time;

import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

public class ObservableGregorianCalendar extends GregorianCalendar {
    private static final String TAG = "ObservableGregorianCalendar-TAG";

    public interface Observer {
        void onSetField(ObservableGregorianCalendar calendar, int field, int value);
        void onSetTime(ObservableGregorianCalendar calendar, long time);
    }

    private Set<Observer> observer;

    public ObservableGregorianCalendar() { }
    public ObservableGregorianCalendar(TimeZone zone) {
        super(zone);
    }
    public ObservableGregorianCalendar(Locale aLocale) {
        super(aLocale);
    }
    public ObservableGregorianCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
    }
    public ObservableGregorianCalendar(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }
    public ObservableGregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        super(year, month, dayOfMonth, hourOfDay, minute);
    }
    public ObservableGregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
        super(year, month, dayOfMonth, hourOfDay, minute, second);
    }

    private Set<Observer> getListeners() {
        if (this.observer == null) this.observer = new LinkedHashSet<>();
        return this.observer;
    }

    public void addObserver(Observer listener) {
        getListeners().add(listener);
    }
    public void removeObserver(Observer listener) {
        if (this.observer == null) return;
        this.observer.remove(listener);
    }

    @Override
    public void set(int field, int value) {
        super.set(field, value);
        if (this.observer == null) return;
        for (Observer observer : getListeners()) observer.onSetField(this, field, value);
    }

    @Override
    public void setTimeInMillis(long millis) {
        super.setTimeInMillis(millis);
        if (this.observer == null) return;
        for (Observer listener : getListeners()) listener.onSetTime(this, millis);
    }

}

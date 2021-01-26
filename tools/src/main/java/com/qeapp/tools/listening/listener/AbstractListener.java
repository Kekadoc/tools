package com.qeapp.tools.listening.listener;

import com.qeapp.tools.listening.subscriber.AbstractSubscriber;
import com.qeapp.util.listening.Subscription;

import java.util.ArrayList;
import java.util.List;

/** Класс обеспечивющий слушание на изменение какого либо объекта.
 * В случае изменения сообщеяет об этом всем подписавшимся на него. */
public abstract class AbstractListener<S extends AbstractSubscriber>
        implements AbstractSubscriber, Subscription<S>
{

    /** Коллекция подписчиков */
    private final List<S> subscribers;

    protected AbstractListener() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(S sub) {
        if (sub == null) return;
        if (this.subscribers.contains(sub)) return;
        this.subscribers.add(sub);
    }
    @Override
    public void unsubscribe(S sub) {
        this.subscribers.remove(sub);
    }

    /** Удалить всех подписчикаов */
    public void removeAllSubs() {
        this.subscribers.clear();
    }
    /** Удалить всех подписчикаов заданных уровней*/
    public void removeAllSubs(int... level) {
        for (int l : level)
            this.subscribers.removeAll(getSubs(l));
    }
    /** Получить полный список подписчиков */
    public List<S> getAllSubs() {
        return subscribers;
    }
    /** Получить полный список подписчиков указанных уровней. */
    public List<S> getAllSubs(int... level) {
        List<S> list = new ArrayList<>();
        for (int l : level)
            list.addAll(getSubs(l));

        return list;
    }

    private List<S> getSubs(int level) {
        List<S> subs = new ArrayList<>();
        for (S sub : subscribers)
            if (sub.getLevel() == level) subs.add(sub);
        return subs;
    }

    @Override
    public String toString() {
        return QeUtil.toSimpleString(this) + " subs count: " + subscribers.size();
    }

}

package com.qeapp.tools.listening.listener;

import com.qeapp.tools.listening.subscriber.Disposable;
import com.qeapp.tools.listening.subscriber.Subscriber;
import com.qeapp.tools.reflection.QeReflection;
import com.qeapp.tools.storage.array.QeArrays;

import java.util.Iterator;

/** Простейший слушатель */
public class Listener extends AbstractListener<Subscriber> implements Subscriber {

    public static int ALL = -9000;

    private static boolean isDisposable(Class<?> clazz, Class<?>...params) {
        return QeReflection.isHaveAnnotation(QeReflection.getMethod(clazz, "onCall", params), Disposable.class)
                || clazz.isAnnotationPresent(Disposable.class);
    }

    public final void call(Integer...levels) {
        Iterator<Subscriber> iterator = getAllSubs().iterator();
        while (iterator.hasNext()) {
            Subscriber sub = iterator.next();
            if (levels[0] != ALL && !QeArrays.isContains(levels, sub.getLevel())) continue;
            sub.onCall();
            if (isDisposable(sub.getClass()))
                iterator.remove();
        }
    }

    public final void call() {
        call(ALL);
    }

    @Override
    public void onCall() {
        call();
    }

    /** Слушатель с параметром */
    public static class Single<A> extends AbstractListener<Subscriber.Single<A>> implements Subscriber.Single<A> {


        public final void call(A param) {
            call(param, ALL);
        }

        public void call(A param, Integer...levels) {
            Iterator<Subscriber.Single<A>> iterator = getAllSubs().iterator();
            while (iterator.hasNext()) {
                Subscriber.Single<A> sub = iterator.next();
                if (levels[0] != ALL && !QeArrays.isContains(levels, sub.getLevel())) continue;
                sub.onCall(param);
                if (isDisposable(sub.getClass(), Object.class))
                    iterator.remove();
            }
        }

        @Override
        public void onCall(A param) {
            call(param);
        }

    }
    /** Слушатель с двумя параметрами */
    public static class Twins<A, B> extends AbstractListener<Subscriber.Twins<A, B>> implements Subscriber.Twins<A, B> {

        public void call(A first, B second, Integer...levels) {
            Iterator<Subscriber.Twins<A, B>> iterator = getAllSubs().iterator();
            while (iterator.hasNext()) {
                Subscriber.Twins<A, B> sub = iterator.next();
                if (levels[0] != ALL && !QeArrays.isContains(levels, sub.getLevel())) continue;
                sub.onCall(first, second);
                if (isDisposable(sub.getClass(), Object.class, Object.class))
                    iterator.remove();
            }
        }

        public void call(A first, B second) {
            call(first, second, ALL);
        }

        @Override
        public void onCall(A first, B second) {
            call(first, second);
        }

    }
    /** Слушатель с двумя параметрами */
    public static class Triple<A, B, C> extends AbstractListener<Subscriber.Triple<A, B, C>> implements Subscriber.Triple<A, B, C> {

        public void call(A first, B second, C third, Integer...levels) {
            Iterator<Subscriber.Triple<A, B, C>> iterator = getAllSubs().iterator();
            while (iterator.hasNext()) {
                Subscriber.Triple<A, B, C> sub = iterator.next();
                if (levels[0] != ALL && !QeArrays.isContains(levels, sub.getLevel())) continue;
                sub.onCall(first, second, third);
                if (isDisposable(sub.getClass(), Object.class, Object.class, Object.class))
                    iterator.remove();
            }
        }

        public void call(A first, B second, C third) {
            call(first, second, third, ALL);
        }

        @Override
        public void onCall(A first, B second, C third) {
            call(first, second, third);
        }

    }

}


package com.qeapp.tools.listening.subscriber;

/** Подписчик */
public interface Subscriber extends AbstractSubscriber {

    /** Оповещение */
    void onCall();

    /** Создание подписчика с указанием уровня */
    abstract class Level extends AbstractSubscriber.Level implements Subscriber {
        public Level(int value) {
            super(value);
        }
    }

    /** Подписчик с параметром */
    interface Single<A> extends AbstractSubscriber {
        
        /** Оповещение */
        void onCall(A param);

        /** Создание подписчика с указанием уровня */
        abstract class Level<A> extends AbstractSubscriber.Level implements Single<A> {
            public Level(int value) {
                super(value);
            }
        }
    }

    /** Подписчик с двумя параметрами */
    interface Twins<A, B> extends AbstractSubscriber {
        /** Оповещение */
        void onCall(A first, B second);

        /** Создание подписчика с указанием уровня */
        abstract class Level<A, B> extends AbstractSubscriber.Level implements Twins<A, B> {
            public Level(int value) {
                super(value);
            }
        }
    }

    /** Подписчик с тремя параметрами */
    interface Triple<A, B, C> extends AbstractSubscriber {

        /** Оповещение */
        void onCall(A first, B second, C third);

        /** Создание подписчика с указанием уровня */
        abstract class Level<A, B, C> extends AbstractSubscriber.Level implements Triple<A, B, C> {
            public Level(int value) {
                super(value);
            }
        }
    }
}

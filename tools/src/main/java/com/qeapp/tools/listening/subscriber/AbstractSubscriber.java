package com.qeapp.tools.listening.subscriber;

public interface AbstractSubscriber {

    /** Отражает уровень подписчика. Уровень позволяет разделять разные типы подписчиков. */
    default int getLevel() {
        return 0;
    }

    /** Класс позволяющий создавать подписчики с указанным уровнем */
    abstract class Level implements AbstractSubscriber {

        private int value;

        public Level(int value) {
            this.value = value;
        }

        @Override
        public int getLevel() {
            return value;
        }
    }

}

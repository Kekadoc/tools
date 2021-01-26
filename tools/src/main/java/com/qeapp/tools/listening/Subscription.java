package com.qeapp.tools.listening;

public interface Subscription<T> {

    void subscribe(T sub);

    void unsubscribe(T sub);

}

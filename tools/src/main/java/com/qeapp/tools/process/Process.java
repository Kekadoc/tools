package com.qeapp.tools.process;

import com.qeapp.tools.fraction.Fraction;

import org.jetbrains.annotations.NotNull;

/**
 * Abstract process
 */
public interface Process extends Fraction {

    interface Observer {
        default void onProcessStarted(Process process) {}
        default void onProcessProgress(Process process, double fraction) {}
        default void onProcessSuccess(Process process) {}
        default void onProcessCancelled(Process process) {}
    }
    interface Observable extends Process {
        void addProcessObserver(@NotNull Observer observer);
        void removeProcessObserver(Observer observer);
    }

    /**
     * Start process
     */
    void start();

    /**
     * Cancel process
     */
    void cancel();

    /**
     * Process is success
     */
    boolean isSuccess();
    /**
     * Process is active
     */
    boolean isActive();
    /**
     * Process is canceled
     */
    boolean isCanceled();

    /**
     * Current progress
     */
    @Override
    double getFraction();

}

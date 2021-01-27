package com.qeapp.tools.process;

import org.jetbrains.annotations.NotNull;

public interface OperatedProcess extends Process {

    interface Observer extends Process.Observer {
        default void onProcessRunned(OperatedProcess process, double runnedProgress) {}
        default void onProcessStopped(OperatedProcess process, double stoppedProgress) {}
    }
    interface Observable extends FailableProcess {
        void addProcessObserver(@NotNull OperatedProcess.Observer observer);
        void removeProcessObserver(OperatedProcess.Observer observer);
    }

    /**
     * Instant end process
     */
    void end();

    /**
     * Pause process
     * @return current progress
     */
    double stop();

    /**
     * Continue process
     * @param progress Progress
     */
    void run(double progress);

    /**
     * Is stopped
     */
    boolean isStopped();

}

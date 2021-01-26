package com.qeapp.tools.process;

import org.jetbrains.annotations.NotNull;

public interface GuidedProcess extends Process {

    interface Observer extends Process.Observer {
        default void onProcessRunned(GuidedProcess process, double runnedProgress) {}
        default void onProcessStopped(GuidedProcess process, double stoppedProgress) {}
    }
    interface Observable extends FailableProcess {
        void addProcessObserver(@NotNull GuidedProcess.Observer observer);
        void removeProcessObserver(GuidedProcess.Observer observer);
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

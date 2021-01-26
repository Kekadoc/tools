package com.qeapp.tools.fraction;

public final class FractionUtils {
    private FractionUtils() {}

    public interface FractionStateEventF {
        default void onChange(float change) {}
        default void onMax() {}
        default void onMin() {}
    }
    public interface FractionStateEventD {
        default void onChange(double change) {}
        default void onMax() {}
        default void onMin() {}
    }

    public static float getFraction(float min, float max, float current) {
        float range = max - min;
        current -= min;
        if (range == 0) return 1.0f;
        return (current / range);
    }
    public static double getFraction(double min, double max, double current) {
        double range = max - min;
        if (range == 0) return 1.0;
        return (current / range);
    }

    public static double getFractionValue(double fraction, double start, double end) {
        return start + fraction * (end - start);
    }
    public static float getFractionValue(float fraction, float start, float end) {
        return start + fraction * (end - start);
    }
    public static int getFractionValue(float fraction, int start, int end) {
        return (int) (start + fraction * (end - start));
    }

    public static float setStandardFraction(float newFraction) {
        return setFraction(0f, 1f, newFraction);
    }
    public static float setStandardFraction(float newFraction, FractionStateEventF eventF) {
        return setFraction(0f, 1f, newFraction, eventF);
    }
    public static double setStandardFraction(double newFraction) {
        return setFraction(0D, 1D, newFraction);
    }
    public static double setStandardFraction(double newFraction, FractionStateEventD eventD) {
        return setFraction(0D, 1D, newFraction, eventD);
    }

    public static float setFraction(float start, float end, float newFraction) {
        if (newFraction > end) newFraction = end;
        else if (newFraction < start) newFraction = start;
        return newFraction;
    }
    public static float setFraction(float start, float end, float newFraction, FractionStateEventF eventF) {
        if (newFraction > end) newFraction = end;
        else if (newFraction < start) newFraction = start;

        if (eventF != null) {
            eventF.onChange(newFraction);
            if (newFraction == start) eventF.onMin();
            else if (newFraction == end) eventF.onMax();
        }

        return newFraction;
    }
    public static double setFraction(double start, double end, double newFraction) {
        if (newFraction > end) newFraction = end;
        else if (newFraction < start) newFraction = start;
        return newFraction;
    }
    public static double setFraction(double start, double end, double newFraction, FractionStateEventD eventD) {
        if (newFraction > end) newFraction = end;
        else if (newFraction < start) newFraction = start;

        if (eventD != null) {
            eventD.onChange(newFraction);
            if (newFraction == start) eventD.onMin();
            else if (newFraction == end) eventD.onMax();
        }

        return newFraction;
    }

}

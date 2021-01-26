package com.qeapp.tools.fraction;

import com.qeapp.tools.observer.ObservableData;

public class ObservableHolderFraction extends ObservableData<ObservableFraction> {

    private final FractionObserver observer = new FractionObserver() {
        @Override
        public void onFractionChange(ObservableFraction fraction, double oldFraction, double newFraction) {
            ObservableHolderFraction.this.onFractionChange(fraction, oldFraction, newFraction);
        }
    };

    protected void onFractionChange(ObservableFraction fraction, double oldFraction, double newFraction) {

    }

    @Override
    protected void onChange(ObservableFraction oldTarget, ObservableFraction newTarget) {
        if (oldTarget != null) oldTarget.removeFractionObserver(this.observer);
        if (newTarget != null) newTarget.addFractionObserver(this.observer);
    }

}

package ru.sbt.mipt.oop.Notification;

public class ContinuousNotifierImpl implements ContinuousNotifier {

    private boolean isActive = false;
    private final Notifier notifier;

    public ContinuousNotifierImpl(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void startNotifying() {
        isActive = true;
        continuousNotify();
    }

    @Override
    public void stopNotifying() {
        isActive = false;
    }

    private void continuousNotify() {
        while (isActive) {
            notifier.notify();
        }
    }
}

package ru.sbt.mipt.oop.Notification;

public class SMSContinuousNotifier implements ContinuousNotifier {

    private boolean isActive = false;
    private final Notifier notifier;
    private final long sleepBreak;

    public SMSContinuousNotifier(Notifier notifier, long sleepBreak) {
        this.notifier = notifier;
        this.sleepBreak = sleepBreak;
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
            try {
                Thread.sleep(sleepBreak);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

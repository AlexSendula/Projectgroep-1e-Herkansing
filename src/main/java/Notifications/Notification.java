package Notifications;

public abstract class Notification {
    abstract String printNotification();

    public final String showNotification() {
        return printNotification();
    }
}

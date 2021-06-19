package Notifications;

public abstract class Notification {
    public final String notification() {
        if (!inputError()) {
            return null;
        }
        return this.getNotification();
    }

    abstract String getNotification();

    boolean inputError() { return false; }
}


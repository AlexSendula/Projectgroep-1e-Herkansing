package Notifications;

import javafx.scene.control.Label;

public abstract class Notification {
    public final String notification(Label label) {
        if (!inputError()) {
            return null;
        }
        return this.getNotification();
    }

    abstract String getNotification();

    boolean inputError() { return false; }
}


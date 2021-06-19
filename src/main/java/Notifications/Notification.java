package Notifications;

import java.awt.*;

public abstract class Notification {
    public final String notification(Label label) {
        if (!inputError()) {
            label.setText(null);
        }
        label.setText(this.getNotification());
        return null;
    }

    abstract String getNotification();

    boolean inputError() { return false; }
}


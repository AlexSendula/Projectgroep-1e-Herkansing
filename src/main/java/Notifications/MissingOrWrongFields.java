package Notifications;

public class MissingOrWrongFields extends Notification {
    @Override
    boolean inputError() { return true; }

    @Override
    public String getNotification() {
        return "Some information is incorrect or missing.!";
    }
}

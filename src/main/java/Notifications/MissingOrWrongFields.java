package Notifications;

public class MissingOrWrongFields extends Notification {
    @Override
    public String getNotification() {
        return "Some information is incorrect or missing.!";
    }
}

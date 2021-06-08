package Notifications;

public class MissingOrWrongFields extends Notification {
    @Override
    String printNotification() {
        return "Some information is incorrect or missing.";
    }
}

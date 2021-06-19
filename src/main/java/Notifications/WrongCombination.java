package Notifications;

public class WrongCombination extends Notification {
    @Override
    public String getNotification() {
        return "Wrong credentials!";
    }
}

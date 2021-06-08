package Notifications;

public class WrongCombination extends Notification {
    @Override
    String printNotification() {
        return "Wrong credentials!";
    }
}

package Notifications;

public class WrongCombination extends Notification {
    @Override
    boolean inputError() { return true; }

    @Override
    public String getNotification() {
        return "Wrong credentials!";
    }
}

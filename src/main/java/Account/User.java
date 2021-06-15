package Account;

import Rewards.Badge;
import Rewards.Jobs.Job;
import Rewards.NoBadge;
import Shop.ShoppingCart;

import java.util.ArrayList;

public class User {
    private String username;
    private Login login;
    private ShoppingCart shoppingCart;
    private Badge badge;
    private ArrayList<Job> job;

    public User(String username, String password){
        this.badge = new NoBadge();
        this.shoppingCart = new ShoppingCart();
        this.username = username;
        this.login = Login.getInstance(this.username, password);
        this.job = new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public Badge getBadge() {
        return badge;
    }

    public void addJob(Job job){
        this.job.add(job);
    }

    public ArrayList<Job> getJob() {
        return job;
    }
}

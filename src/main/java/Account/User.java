package Account;

import Rewards.*;
import Rewards.Jobs.Job;
import Shop.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class User {
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("date")
    private String date;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("email")
    private String email;
    @JsonProperty("badge")
    private Badge badge;
    private Login login;
    private ShoppingCart shoppingCart;
    @JsonProperty("jobs")
    private ArrayList<Job> jobs;

    public User() {
        this.shoppingCart = new ShoppingCart();
    }

    public User(String username){
        this.badge = new NoBadge();
        this.shoppingCart = new ShoppingCart();
        this.username = username;
        this.jobs = new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
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
        this.jobs.add(job);
    }

    public ArrayList<Job> getJob() {
        return jobs;
    }
}

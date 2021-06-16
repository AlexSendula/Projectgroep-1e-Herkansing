package Account;

import Rewards.*;
import Rewards.Jobs.Job;
import Shop.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Login login;
    @JsonProperty("shoppingcart")
    private ShoppingCart shoppingCart;
    @JsonProperty("jobs")
    private ArrayList<Job> job;

    public User() {
        this.shoppingCart = new ShoppingCart();
    }

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(String[] userInformation){
        this.firstname = userInformation[0];
        this.lastname = userInformation[1];
        this.date = userInformation[2];
        this.username = userInformation[3];
        this.password = userInformation[4];
        this.email = userInformation[5];
        this.badge = new NoBadge();
        this.shoppingCart = new ShoppingCart();
        this.job = new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
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

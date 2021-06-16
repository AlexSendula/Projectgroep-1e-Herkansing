package Account;

import Rewards.*;
import Rewards.Jobs.Job;
import Shop.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    public User(String firstname, String lastname, String date, String username, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.date = date;
        this.username = username;
        this.password = password;
        this.email = email;
        this.badge = new NoBadge();
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

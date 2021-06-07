package Account;

public class User {
    private String username;
    private Login login;

    public User(String username, String password){
        this.username = username;
        this.login = Login.getInstance(this.username, password);
    }

    public String getUsername() {
        return this.username;
    }

    public Login getLogin() {
        return login;
    }
}

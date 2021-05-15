package classes;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private static User single_instance = null;

    private User(int id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public static User getInstance(String username)
    {
        if (single_instance == null)
//            TODO: Gegevens uit file laten komen.
            single_instance = new User(1, username, username+"12", "a@a.nl");

        return single_instance;
    }

    public static void logOut(){
        single_instance = null;
    }

    public String getPassword() {
        return password;
    }
}

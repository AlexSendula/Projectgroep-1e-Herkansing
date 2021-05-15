package classes;

public class Login {
    public boolean validateLogin(String password, String correctPassword){
        if (password.equals(correctPassword)){
            return true;
        } else {
            return false;
        }
    }
}

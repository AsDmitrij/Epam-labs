package service;

import model.User;

public class UserCreator {

    public static final String USER_MAIL = "simplecloudforonetime@yandex.by";
    public static final String USER_PASSWORD = "testPassword";
    public static final String USER_NAME = "simplecloudforonetime";

    public static User withCredentialsFromProperty(){
        return new User(USER_NAME,
                USER_PASSWORD,
                USER_MAIL);
    }

    public static User withEmptyUsernameAndUserMail(){
        return new User("", USER_PASSWORD,"");
    }

    public static User withEmptyPasswordAndUserMail(){
        return new User(USER_NAME, "","");
    }
    public static User withEmptyPasswordAndUsername(){
        return new User("","",USER_MAIL);
    }
}

package test;

import model.User;
import org.testng.annotations.Test;
import page.MainPage;
import service.UserCreator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAccessTest extends CommonConditions{

    @Test
    public void userCanLoginTest() {
        User testUser = UserCreator.withCredentialsFromProperty();
        String userWelcomeName = new MainPage(driver).
                openPage()
                .login(testUser)
                .openPage()
                .getUserWelcomeName();
        assertThat(userWelcomeName, is(equalTo(testUser.getUserMail())));
    }
}

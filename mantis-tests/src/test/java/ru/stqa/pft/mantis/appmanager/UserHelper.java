package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends BaseHelper {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void goToPageSettingsUsers() {
        wd.get(app.getProperty("web.baseUrl") + "manage_user_page.php");
    }

    public void adminLogin(String login, String password) {
        type(By.name("username"), login);
        click(By.cssSelector("input.btn-success"));
        type(By.name("password"), password);
        click(By.cssSelector("input.btn-success"));
    }

    public void resetPassword(String username) {
        click(By.linkText(username));
        click(By.cssSelector("input[value='—бросить пароль']"));
    }

    public String generatedPassword() {
        int a = 100000;
        int b = 900000;
        int x = a + (int) (Math.random() * ((b - a) + 1));
        return Integer.toString(x);
    }

    public void finish(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("button.btn-success"));
    }
}

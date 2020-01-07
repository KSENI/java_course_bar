package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends BaseTest {

    @BeforeMethod
    public void startMailServer() {
        app.mailHelper().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException {
        app.user().adminLogin("administrator", "root");
        app.user().goToPageSettingsUsers();

        UserData situatedUser = app.dbHelper().getUserData().iterator().next();
        String situatedUserEmail = situatedUser.getEmail();
        String userName = situatedUser.getUsername();
        app.user().resetPassword(userName);

        List<MailMessage> mailMessages = app.mailHelper().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, situatedUserEmail);

        String newPassword = app.user().generatedPassword();
        app.user().finish(confirmationLink, newPassword);

        HttpSession session = app.newSession();
        assertTrue(session.login(userName, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mailHelper().stop();
    }
}


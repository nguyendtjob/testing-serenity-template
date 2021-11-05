package be.fgov.health.project.e2etests.tests.homepage;

import be.fgov.health.project.e2etests.steps.homepage.HomepageSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class HomepageTest {

    @Managed
    private WebDriver notUsedButNecessaryForSerenity;

    @Steps(shared = true)
    private HomepageSteps homepageSteps;

    @Before
    public void login_then_mainPage(){ homepageSteps.proceedLoginThenGoToMainPage(); }

    @Test
    public void homepage_sampleTest() {
        homepageSteps.goToHomepage();
    }
}

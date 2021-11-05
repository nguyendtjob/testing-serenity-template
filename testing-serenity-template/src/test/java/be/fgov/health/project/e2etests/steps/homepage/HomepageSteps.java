package be.fgov.health.project.e2etests.steps.homepage;

import be.fgov.health.project.e2etests.pages.homepage.Homepage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class HomepageSteps extends ScenarioSteps {
    private Homepage homepage;

    @Step
    public void proceedLoginThenGoToMainPage(){ homepage.loginThenMainPage(); }

    @Step("Go to the homepage")
    public void goToHomepage(){ homepage.reachPage(); }


}

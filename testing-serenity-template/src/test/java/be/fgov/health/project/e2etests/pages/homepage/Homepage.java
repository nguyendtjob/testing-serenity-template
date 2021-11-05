package be.fgov.health.project.e2etests.pages.homepage;

import be.fgov.health.project.e2etests.pages.common.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Homepage extends CommonPage {

    public Homepage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void reachPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomepageElement.MENU_HOME.path)));
        click_ignoreException(HomepageElement.MENU_HOME.path);
    }



    public enum HomepageElement{
        MENU_HOME("");

        public final String path;

        HomepageElement(String path){
            this.path = path;
        }
    }
}

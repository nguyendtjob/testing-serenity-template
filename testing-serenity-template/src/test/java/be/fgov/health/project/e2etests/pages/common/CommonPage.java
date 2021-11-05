package be.fgov.health.project.e2etests.pages.common;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class CommonPage extends PageObject {
    private static final String USERNAME_FIELD = "//input[@name='username']";
    private static final String PASSWORD_FIELD = "//input[@name='password']";
    private static final String LOGIN_BUTTON = "//input[@name='login']";
    private static final String LOADING_BAR = "";
    private static final String HEADER_LOGO = "";
    public static final long TIMEOUT_BASE = 60;
    public static final long TIMEOUT_LOADING = 120;

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected WebDriverWait waitLoading;

    public CommonPage(WebDriver driver) {
        super(driver);
        this.driver = getDriver();
        this.wait = new WebDriverWait(driver, TIMEOUT_BASE);
        this.waitLoading = new WebDriverWait(driver, TIMEOUT_LOADING);
        this.actions = new Actions(driver);
    }

    public abstract void reachPage();

    /**
     * Method used to create the browser window, access to the login page then to the main page
     * @author NGUYEN Dat Toan
     */
    public void loginThenMainPage(){
        wait = new WebDriverWait(driver, TIMEOUT_BASE);
        waitLoading = new WebDriverWait(driver, TIMEOUT_LOADING);

        open();

        driver.manage().window().maximize();

        //Login
        WebElementFacade elementName = find(By.xpath(USERNAME_FIELD));
        WebElementFacade elementPass = find(By.xpath(PASSWORD_FIELD));
        WebElementFacade loginButton = find(By.xpath(LOGIN_BUTTON));
        String username = "";
        String password = "";
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("secret.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Unable to find secret.properties");
                return;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        typeInto(elementName, username);
        typeInto(elementPass, password);
        loginButton.click();

        open();
        /* Might be required if a company selection is required for the application before reaching it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("")));
        click_ignoreException("");
        */
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HEADER_LOGO)));
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Click on the element while ignoring possible exception due to Selenium
     * @param path String indicating the xpath of the element to click on
     * @author NGUYEN Dat Toan
     */
    public void click_ignoreException(String path){
        int clickAttempts = 0;
        while (clickAttempts < 10){
            try{
                find(By.xpath(path)).click();
                break;
            }catch(ElementShouldBeEnabledException | ElementNotInteractableException | StaleElementReferenceException e){
                e.printStackTrace();
            }
            clickAttempts++;
        }
    }

    /**
     * Force the Selenium script to wait until the loading screen disappears.
     * @author NGUYEN Dat Toan
     */
    public void waitForLoading() {
        //Waiting for the loading bar to disappear
        waitLoading.until((ExpectedCondition<Boolean>) driver -> {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOADING_BAR)));
            WebElement loadingBar = driver.findElement(By.xpath(LOADING_BAR));
            String enabled = loadingBar.getAttribute("aria-hidden");
            if(enabled == null)
                return true;
            return enabled.equals("true");
        });
    }

    /**
     * Force the Selenium script to wait a specific amount of time
     * @author NGUYEN Dat Toan
     */
    public void customWait(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

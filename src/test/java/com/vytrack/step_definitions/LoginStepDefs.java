package com.vytrack.step_definitions;

import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginStepDefs {

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page()  {
        System.out.println("I opened the browser navigated to vytrack login page");
        String url = ConfigurationReader.get("url");

        //TestNG Type
        //WebDriver driver = Driver.get();
        //driver.get(url);

        Driver.get().get(url);

    }

    @When("the user enter the driver information")
    public void the_user_enter_the_driver_information()  {
        System.out.println("I put user1 username and UserUser123 password and click login button ");
        String name = ConfigurationReader.get("driver_username");
        String password = ConfigurationReader.get("driver_password");
        LoginPage loginPage = new LoginPage();
        loginPage.login(name,password);
    }

    @Then("the user should be able to login")
    public void the_user_should_be_able_to_login()  {
        System.out.println("I verify that title changed to Dashboard");
        String actualTitle = Driver.get().getTitle();
        Assert.assertEquals("Verify Title","Dashboard",actualTitle);
    }

    @When("the user enter sales manager information")
    public void the_user_enter_sales_manager_information() {
        System.out.println("I put salesmanager85 and User123");
        String name = ConfigurationReader.get("sales_manager_username");
        String password = ConfigurationReader.get("sales_manager_password");
        LoginPage loginPage = new LoginPage();
        loginPage.login(name,password);
    }

    @When("the user enter store manager information")
    public void the_user_enter_store_manager_information() {
        System.out.println("I put storemanager101 and UserUser123");
        String name = ConfigurationReader.get("store_manager_username");
        String password = ConfigurationReader.get("store_manager_password");
        LoginPage loginPage = new LoginPage();
        loginPage.login(name,password);
    }

    @When("the user logs in using {string} and {string}")
    public void theUserLogsInUsingAnd(String name, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(name,password);
    }

    @And("the title contains {string}")
    public void theTitleContains(String expectedTitle) {

        BrowserUtils.waitFor(5);
        System.out.println("expectedTitle = " + expectedTitle);
        Assert.assertTrue(Driver.get().getTitle().contains(expectedTitle));
    }

    @And("the user enter as {string}")
    public void theUserEnterAs(String str) {

        str = str.replace(" ","_");
        String name = ConfigurationReader.get(str + "username");
        String password = ConfigurationReader.get(str + "password");
        LoginPage loginPage = new LoginPage();
        loginPage.login(name,password);


    }
}

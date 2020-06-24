package com.vytrack.step_definitions;

import com.vytrack.pages.BasePage;
import com.vytrack.pages.ContactsPage;
import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ContactsStepDefs {

    @Given("the user logged as {string}")
    public void the_user_logged_as(String userType) {

        Driver.get().get(ConfigurationReader.get("url"));

        String username = null;
        String password=null;

        if (userType.equals("driver")){
            username = ConfigurationReader.get("driver_username");
            password =ConfigurationReader.get("driver_password");
        } else  if (userType.equals("sales manager")){
            username = ConfigurationReader.get("sales_manager_username");
            password =ConfigurationReader.get("sales_manager_password");
        } else  if (userType.equals("store manager")){
            username = ConfigurationReader.get("store_manager_username");
            password =ConfigurationReader.get("store_manager_password");
        }

        new LoginPage().login(username,password);

    }

    @Then("the user should see following options")
    public void the_user_should_see_following_options(List<String> menuOptions) {
        System.out.println(menuOptions.size());
        System.out.println(menuOptions);

       List<String> actualOptions = BrowserUtils.getElementsText(new DashboardPage().menuOptions);
        Assert.assertEquals(menuOptions,actualOptions);
        System.out.println("actualOptions = " + actualOptions);
        System.out.println("menuOptions = " + menuOptions);

    }

    @When("the user logs in using following credentials")
    public void the_user_logs_in_using_following_credentials(Map<String,String> userInfo) {
        System.out.println("userInfo = " + userInfo);

        new LoginPage().login(userInfo.get("username"),userInfo.get("password"));
        String expected = userInfo.get("firstname") + " " + userInfo.get("lastname");
        String actual = new DashboardPage().userName.getText(); //new DashboardPage().getUserName();
        Assert.assertEquals(actual,expected);



    }

    @When("the user clicks {string} from contacts")
    public void theUserClicksFromContacts(String email) {
        //click the row with email
        BrowserUtils.waitFor(3);
        new ContactsPage().getContactEmail(email).click();

    }


    @Then("the information should be same with database")
    public void theInformationShouldBeSameWithDatabase() {
        BrowserUtils.waitFor(3);
    }
}

package com.vytrack.step_definitions;

import com.vytrack.pages.*;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.DBUtils;
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
        //get actual data from UI
        ContactInfoPage contactInfoPage=new ContactInfoPage();
        String actualFullName = contactInfoPage.contactFullName.getText();
        String actualEmail = contactInfoPage.email.getText();
        String actualPhone= contactInfoPage.phone.getText();

        System.out.println("actualFullName = " + actualFullName);
        System.out.println("actualEmail = " + actualEmail);
        System.out.println("actualPhone = " + actualPhone);

        //get expected data from database
        String query = "select concat (first_name,' ',last_name) as fullname , e.email,phone \n" +
                "from orocrm_contact c JOIN orocrm_contact_email e\n" +
                "ON c.id=e.owner_id\n" +
                "JOIN orocrm_contact_phone p\n" +
                "ON e.owner_id=p.owner_id\n" +
                "where e.email = 'mbrackstone9@example.com'";
        //get connection
        DBUtils.createConnection();

        //create data
        //if you are dealing with multiple rows use List<Map<String,Object>>
        Map<String,Object> rowMap = DBUtils.getRowMap(query);
        System.out.println(rowMap);

        //String expectedFirst = (String) rowMap.get("first_name");
        //String expectedLast = (String) rowMap.get("last_name");

        String expectedFull = (String) rowMap.get("fullname");
        String expectedEmail = (String) rowMap.get("email");
        String expectedPhone = (String) rowMap.get("phone");

        System.out.println("expectedFull = " + expectedFull);
        System.out.println("expectedEmail = " + expectedEmail);
        System.out.println("expectedPhone = " + expectedPhone);


        //close connection
        DBUtils.destroy();


        //Compare UI to DB


        Assert.assertEquals(expectedFull,actualFullName);
        Assert.assertEquals(expectedEmail,actualEmail);
        Assert.assertEquals(expectedPhone,actualPhone);



    }

    @Then("the information {string} should be same with database")
    public void theInformationShouldBeSameWithDatabase(String string) {

        BrowserUtils.waitFor(3);
        //get actual data from UI
        ContactInfoPage contactInfoPage=new ContactInfoPage();
        String actualFullName = contactInfoPage.contactFullName.getText();
        String actualEmail = contactInfoPage.email.getText();
        String actualPhone= contactInfoPage.phone.getText();

        System.out.println("actualFullName = " + actualFullName);
        System.out.println("actualEmail = " + actualEmail);
        System.out.println("actualPhone = " + actualPhone);

        //get expected data from database
        String query = "select concat (first_name,' ',last_name) as fullname , e.email,phone \n" +
                "from orocrm_contact c JOIN orocrm_contact_email e\n" +
                "ON c.id=e.owner_id\n" +
                "JOIN orocrm_contact_phone p\n" +
                "ON e.owner_id=p.owner_id\n" +
                "where e.email = '" + string + "'";
        //get connection
        //DBUtils.createConnection();

        //create data
        //if you are dealing with multiple rows use List<Map<String,Object>>
        Map<String,Object> rowMap = DBUtils.getRowMap(query);
        System.out.println(rowMap);

        //String expectedFirst = (String) rowMap.get("first_name");
        //String expectedLast = (String) rowMap.get("last_name");

        String expectedFull = (String) rowMap.get("fullname");
        String expectedEmail = (String) rowMap.get("email");
        String expectedPhone = (String) rowMap.get("phone");

        System.out.println("expectedFull = " + expectedFull);
        System.out.println("expectedEmail = " + expectedEmail);
        System.out.println("expectedPhone = " + expectedPhone);


        //close connection
        //DBUtils.destroy();


        //Compare UI to DB


        Assert.assertEquals(expectedFull,actualFullName);
        Assert.assertEquals(expectedEmail,actualEmail);
        Assert.assertEquals(expectedPhone,actualPhone);



    }
}

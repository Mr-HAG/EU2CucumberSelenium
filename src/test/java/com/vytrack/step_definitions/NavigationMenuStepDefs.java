package com.vytrack.step_definitions;

import com.vytrack.pages.ContactsPage;
import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class NavigationMenuStepDefs {

    @When("the user navigate to fleet,vehicles")
    public void the_user_navigate_to_fleet_vehicles() {
        System.out.println("navigate to fleet,vehicles");
        //BrowserUtils.waitFor(20);
        //new DashboardPage().navigateToModule("Fleet","Vehicles");

    }

    @Then("the title should be vehicles")
    public void the_title_should_be_vehicles() {
        System.out.println("verify title is vehicles");
        //String actualTitle = Driver.get().getTitle();
        //Assert.assertEquals("verify title","Vehicles",actualTitle);
    }

    @When("the user navigate to marketing,campaigns")
    public void the_user_navigate_to_marketing_campaigns() {
        System.out.println("navigate to marketing,campaigns");
        //BrowserUtils.waitFor(10);
        //new DashboardPage().navigateToModule("Marketing","Campaigns");
    }

    @Then("the title should be campaigns")
    public void the_title_should_be_campaigns() {
        System.out.println("verify title is campaigns");
        //String actualTitle = Driver.get().getTitle();
        //Assert.assertEquals("verify title","Campaigns",actualTitle);
    }

    @When("the user navigate to activates,calender events")
    public void the_user_navigate_to_activates_calender_events() {
        System.out.println("navigate to activates,calender events");
        //BrowserUtils.waitFor(10);
        //new DashboardPage().navigateToModule("Activities","Calender Events");

    }

    @Then("the title should be calender events")
    public void the_title_should_be_calender_events() {
        System.out.println("verify title is calender event");
        //String actualTitle = Driver.get().getTitle();
        //Assert.assertEquals("verify title","Activities",actualTitle);
    }

    @When("the user navigates {string} {string}")
    public void the_user_navigates(String tab, String module) {
        new DashboardPage().navigateToModule(tab,module);
    }

    @Then("default page number should be {int}")
    public void default_page_number_should_be(Integer expectedPageNumber) {
        BrowserUtils.waitFor(2);
        ContactsPage contactsPage = new ContactsPage();
        Integer actualNumber = Integer.parseInt(contactsPage.pageNumber.getAttribute("value"));

        Assert.assertEquals(expectedPageNumber,actualNumber);


    }


}

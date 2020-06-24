package com.vytrack.step_definitions;

import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;


public class Hooks {

    @Before
    public void setUp(){

        //System.out.println("\tThis is coming from BEFORE");
        //String url = ConfigurationReader.get("url");
        //Driver.get().get(url);
        //String name = ConfigurationReader.get("sales_manager_username");
        //String password = ConfigurationReader.get("sales_manager_password");
        //LoginPage loginPage = new LoginPage();
        //loginPage.login(name,password);

        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){

            final byte[] screenshot = ((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");

        }



        Driver.closeDriver();

    }

    @Before("@db")
    public void set(){
        System.out.println("\tconnecting to database");
    }
    @After("@db")
    public void close(){
        System.out.println("\tdisconnecting from database");
    }

}


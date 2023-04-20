package stepDefinitions;

import base.BaseTest;
import com.cucumberTestng.pageObjects.HomePage;
import com.cucumberTestng.pageObjects.ListingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.List;

@Slf4j
public class GoogleTestSteps extends BaseTest {

    HomePage homePage;
    ListingPage listingPage;

    String searchString;

    @Given("^user visit google homepage (.*?)$")
    public void userVisitHomepage(String url) {
        homePage = new HomePage(driver);
        homePage.visitUrl(url);
    }

    @Given("user search keyword {string}")
    public void userSearchKeywordTest(String searchText) {
        homePage.searchText(searchText);
    }

    @Then("user should be redirected to search listing page")
    public void userShouldBeRedirectedToSearchListingPage() {
        listingPage = new ListingPage(driver);
        Assert.assertTrue(listingPage.validatePageLoad());

    }
}

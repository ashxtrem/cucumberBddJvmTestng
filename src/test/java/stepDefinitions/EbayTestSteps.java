package stepDefinitions;

import com.cucumberTestng.base.BaseTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.List;

@Slf4j
public class EbayTestSteps extends BaseTest {

    HomePage homePage;
    CategoryPage categoryPage;
    ListingPage listingPage;

    String searchString;


    @Given("user click on Shop by category dropdown")
    public void userClickOnShopByCategory() {
        homePage.openCategoryDropDown();
    }

    @And("user click on Cell Phones & Accessories under electronics category")
    public void userClickOnCellPhonesAccessoriesUnderElectronics() {
        homePage.clickCellPhonesAccessoriesHyperLink();
    }

    @Then("^user should be redirected to (.*?) subpage$")
    public void userShouldBeRedirectedSubpage(String subPageText) {
        categoryPage = new CategoryPage(driver);
        Assert.assertEquals(subPageText, categoryPage.getBreadCrumbLabelText(subPageText));

    }

    @When("user click on Cell Phones & Smartphones link in the left hand side navigation section")
    public void userClickOnItemNavigationSection() {
        categoryPage.clickCellPhonesAccessoriesNavigationBtn();
    }


    @When("user click on see all link under shop by brand section")
    public void userClickAllLink() {
        categoryPage.clickSeeAllBtn();
    }

    @Then("^filter popup should be (.*?)$")
    public void filterPopupShouldBeLoaded(String state) {
        switch (state) {
            case "loaded" -> Assert.assertTrue(categoryPage.isFilterFormLoaded());
            case "unloaded" -> Assert.assertFalse(categoryPage.isFilterFormLoaded());
            default -> throw new RuntimeException("Incorrect form state sent via feature step: "+state);
        }
    }

    @And("user select filters")
    public void userSelectFilters(DataTable filterData) {
        List<List<String>> filterList = filterData.asLists();
        log.info("Filters: "+filterList.toString());

        int counter = 0;

        while (filterList.get(0).size() > counter ) {
            String filterName = filterList.get(0).get(counter);
            log.info(filterName);
            categoryPage.applyFilter(filterName);
            counter++;
        }
    }

    @When("user click on apply button")
    public void userClickOnApplyButton() {
        categoryPage.applyFilters();
    }

    @Then("user should be redirected to filter applicable page")
    public void userShouldBeRedirectedToListingPage() {
        Assert.assertTrue(categoryPage.isFilterListingPageLoaded());
    }

    @When("user click on applied filter drop down")
    public void userClickOnAppliedFilterDropDown() {
        categoryPage.clickAppliedFilter();
    }

    @Then("user validate applied filters should be same")
    public void userValidateAppliedFiltersShouldBeSame() {
        Assert.assertTrue(categoryPage.verifyAppliedFilters());
    }

    @Given("^user type (.*?) in the search bar?")
    public void userInputSearchBar(String searchText) {
        searchString = searchText;
        homePage.searchInput(searchText);
    }

    @And("^user select (.*?) from select a category for search$")
    public void userSelectACategoryForSearch(String categoryOption) {
        homePage.selectFromCategories(categoryOption);
    }

    @When("user click on search button")
    public void userClickOnSearchButton() {
        homePage.clickSearchBtn();
    }

    @Then("listing page should be loaded")
    public void listingPageShouldBeLoaded() {
        listingPage = new ListingPage(driver);
        Assert.assertTrue(listingPage.verifyPageHeaderVisible());
    }

    @And("user verify that the first result name matches with the search string")
    public void userVerifyFirstSearch() {
        Assert.assertTrue(listingPage.getFirstItemText().toLowerCase().contains(searchString.toLowerCase()));
    }

    @And("validate listing page header")
    public void validateListingPageHeader(DataTable expectedHeaderTable) {
        List<List<String>> expectedHeaderList = expectedHeaderTable.asLists();
        Assert.assertEquals(expectedHeaderList.get(0).get(0), categoryPage.returnHeaderText());
    }

    @Given("^user visit ebay homepage (.*?)$")
    public void userVisitHomepage(String url) {
        homePage = new HomePage(driver);
        homePage.visitUrl(url);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewsletterPage {
    private final WebDriver driver;

    // Simple locators
    private final By emailInput = By.tagName("input");
    private final By submitButton = By.tagName("button");

    // Additional locators needed for the test
    private final By pageHeader = By.className("content-header");
    private final By pageImage = By.className("card-right");
    private final By errorMessage = By.className("error-msg");

    public NewsletterPage(WebDriver driver) {
        this.driver = driver;
        System.out.println("📄 NewsletterPage initialized");
    }

    public void enterEmail(String email) {
        try {
            WebElement emailField = driver.findElement(emailInput);
            emailField.clear();
            emailField.sendKeys(email);
            System.out.println("📧 Email entered: " + email);
        } catch (Exception e) {
            System.out.println("❌ Could not enter email: " + e.getMessage());
        }
    }

    public void clickSubmitButton() {
        try {
            WebElement button = driver.findElement(submitButton);
            button.click();
            System.out.println("🖱️ Submit button clicked");
        } catch (Exception e) {
            System.out.println("❌ Could not click submit button: " + e.getMessage());
        }
    }

    public void subscribeWithEmail(String email) {
        enterEmail(email);
        clickSubmitButton();
    }

    public boolean isEmailInputDisplayed() {
        try {
            WebElement emailField = driver.findElement(emailInput);
            return emailField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubmitButtonDisplayed() {
        try {
            WebElement button = driver.findElement(submitButton);
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // NEW: Method to check page header
    public boolean isPageHeaderDisplayed() {
        try {
            WebElement header = driver.findElement(pageHeader);
            boolean isDisplayed = header.isDisplayed();
            System.out.println("📄 'Stay updated!' header found: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("❌ 'Stay updated!' header not found");
            return false;
        }
    }

    // NEW: Method to check page image
    public boolean isPageImageDisplayed() {
        try {
            WebElement image = driver.findElement(pageImage);
            boolean isDisplayed = image.isDisplayed();
            System.out.println("🖼️ Page image found: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("❌ Page image not found");
            return false;
        }
    }

    // NEW: Method to check error message
    public boolean isErrorMessageDisplayed() {
        try {
            WebElement error = driver.findElement(errorMessage);
            boolean isDisplayed = error.isDisplayed();
            System.out.println("⚠️ Error message found: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("❌ Error message not found");
            return false;
        }
    }

    // NEW: Method to get error message text
    public String getErrorMessage() {
        try {
            WebElement error = driver.findElement(errorMessage);
            String errorText = error.getText();
            System.out.println("⚠️ Error message: " + errorText);
            return errorText;
        } catch (Exception e) {
            System.out.println("❌ Could not get error message");
            return "";
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
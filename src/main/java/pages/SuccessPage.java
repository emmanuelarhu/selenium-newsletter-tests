package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessPage {
    private final WebDriver driver;

    // Simple locators for the success modal
    private final By thanksMessage = By.className("msg-header");
    private final By dismissButton = By.className("msg-button");
    private final By successIcon = By.className("msg-icon");

    // Additional locator for email confirmation
    private final By confirmationText = By.className("msg-email");

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
        System.out.println("🎯 SuccessPage initialized");
    }

    public boolean isSuccessDisplayed() {
        try {
            WebElement thanksElement = driver.findElement(thanksMessage);
            boolean isDisplayed = thanksElement.isDisplayed();
            System.out.println("✅ Success modal found: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("❌ Success modal not found");
            return false;
        }
    }

    public boolean isSuccessIconDisplayed() {
        try {
            WebElement iconElement = driver.findElement(successIcon);
            boolean isDisplayed = iconElement.isDisplayed();
            System.out.println("✅ Success icon: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("❌ Success icon not found");
            return false;
        }
    }

    public String getSuccessMessage() {
        try {
            WebElement thanksElement = driver.findElement(thanksMessage);
            String message = thanksElement.getText();
            System.out.println("📄 Success message: " + message);
            return message;
        } catch (Exception e) {
            System.out.println("❌ Could not get success message");
            return "";
        }
    }

    // NEW: Method to verify email in confirmation
    public boolean verifyEmailInConfirmation(String expectedEmail) {
        try {
            WebElement confirmationElement = driver.findElement(confirmationText);
            String confirmationText = confirmationElement.getText();
            boolean emailMatches = confirmationText.contains(expectedEmail);
            System.out.println("📧 Email verification: Expected=" + expectedEmail + ", Found=" + emailMatches);
            System.out.println("📧 Confirmation text: " + confirmationText);
            return emailMatches;
        } catch (Exception e) {
            System.out.println("❌ Could not verify email in confirmation");
            return false;
        }
    }

    public void clickDismissButton() {
        try {
            WebElement button = driver.findElement(dismissButton);
            button.click();
            System.out.println("✅ Dismiss button clicked");
        } catch (Exception e) {
            System.out.println("❌ Could not click dismiss button");
        }
    }

    public boolean isDismissButtonVisible() {
        try {
            WebElement button = driver.findElement(dismissButton);
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
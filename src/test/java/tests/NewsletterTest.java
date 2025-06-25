package tests;

import base.BaseTest;
import pages.NewsletterPage;
import pages.SuccessPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class NewsletterTest extends BaseTest {

    @Test
    @DisplayName("Complete Newsletter Subscription Flow")
    public void testCompleteNewsletterFlow() {
        System.out.println("🚀 Starting complete newsletter subscription flow test");

        // Step 1: Navigate to https://newsletter-signup-form.vercel.app/
        navigateToHomePage();

        // Create page objects
        NewsletterPage newsletterPage = new NewsletterPage(driver);
        SuccessPage successPage = new SuccessPage(driver);

        // Step 2: Verify email input and submit button are visible
        System.out.println("🔍 Step 2: Verifying email input and submit button...");
        Assertions.assertTrue(newsletterPage.isEmailInputDisplayed(),
                "Email input should be visible");
        Assertions.assertTrue(newsletterPage.isSubmitButtonDisplayed(),
                "Submit button should be visible");

        // Step 3: Verify image is visible
        System.out.println("🔍 Step 3: Verifying page image...");
        Assertions.assertTrue(newsletterPage.isPageImageDisplayed(),
                "Page image should be visible");

        // Step 4: Verify the page header "Stay updated!" is present
        System.out.println("🔍 Step 4: Verifying page header...");
        Assertions.assertTrue(newsletterPage.isPageHeaderDisplayed(),
                "'Stay updated!' header should be present");

        // Step 5: Enter invalid email in the input field
        System.out.println("🔍 Step 5: Testing invalid email...");
        String invalidEmail = "ss";
        newsletterPage.enterEmail(invalidEmail);

        // Step 6: Click on the "Subscribe to monthly newsletter" button
        System.out.println("🔍 Step 6: Clicking submit with invalid email...");
        newsletterPage.clickSubmitButton();

        // Wait for error message to appear
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Step 7: Check if you get error message "Valid email required"
        System.out.println("🔍 Step 7: Verifying error message...");
        Assertions.assertTrue(newsletterPage.isErrorMessageDisplayed(),
                "Error message should appear for invalid email");
        String errorMessage = newsletterPage.getErrorMessage();
        Assertions.assertTrue(errorMessage.contains("Valid email required"),
                "Error message should say 'Valid email required'");

        // Step 8: Enter valid email "gtp.2025@amalitechtraining.org" in the input field
        System.out.println("🔍 Step 8: Entering valid email...");
        String validEmail = "gtp.2025@amalitechtraining.org";
        newsletterPage.enterEmail(validEmail);

        // Step 9: Click "Subscribe to monthly newsletter" button
        System.out.println("🔍 Step 9: Clicking submit with valid email...");
        newsletterPage.clickSubmitButton();

        // Step 10: Wait 3 seconds for the success modal to appear
        System.out.println("🔍 Step 10: Waiting for success modal...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Step 11: Verify success modal shows "Thanks for subscribing!"
        System.out.println("🔍 Step 11: Verifying success modal...");
        Assertions.assertTrue(successPage.isSuccessDisplayed(),
                "Success modal should appear");
        String successMessage = successPage.getSuccessMessage();
        Assertions.assertTrue(successMessage.contains("Thanks for subscribing"),
                "Success message should contain 'Thanks for subscribing'");

        // Step 12: Verify the message icon shows
        System.out.println("🔍 Step 12: Verifying success icon...");
        Assertions.assertTrue(successPage.isSuccessIconDisplayed(),
                "Success icon (checkmark) should be visible");

        // Step 13: Verify the exact email entered in the input field shows
        System.out.println("🔍 Step 13: Verifying email in confirmation...");
        Assertions.assertTrue(successPage.verifyEmailInConfirmation(validEmail),
                "Confirmation should show the email address: " + validEmail);

        // Step 14: Click "Dismiss message" button
        System.out.println("🔍 Step 14: Clicking dismiss button...");
        Assertions.assertTrue(successPage.isDismissButtonVisible(),
                "Dismiss button should be visible");
        successPage.clickDismissButton();

        // Step 15: Wait 3 seconds for the newsletter page to appear
        System.out.println("🔍 Step 15: Waiting to return to newsletter page...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Step 16: Verify email input and submit button are visible (back to main page)
        System.out.println("🔍 Step 16: Verifying we're back on newsletter page...");
        Assertions.assertTrue(newsletterPage.isEmailInputDisplayed(),
                "Email input should be visible again");
        Assertions.assertTrue(newsletterPage.isSubmitButtonDisplayed(),
                "Submit button should be visible again");

        // Step 17: Verify image is visible (back to main page)
        System.out.println("🔍 Step 17: Verifying page image again...");
        Assertions.assertTrue(newsletterPage.isPageImageDisplayed(),
                "Page image should be visible again");

        // Step 18: Verify the page header "Stay updated!" is present (back to main page)
        System.out.println("🔍 Step 18: Verifying page header again...");
        Assertions.assertTrue(newsletterPage.isPageHeaderDisplayed(),
                "'Stay updated!' header should be present again");

        System.out.println("✅ Complete newsletter subscription flow test passed successfully!");
    }

    @Test
    @DisplayName("Test Page Elements Only")
    public void testPageElements() {
        System.out.println("🚀 Testing page elements");

        navigateToHomePage();
        NewsletterPage newsletterPage = new NewsletterPage(driver);

        // Check if main elements are present
        Assertions.assertTrue(newsletterPage.isEmailInputDisplayed(), "Email input should exist");
        Assertions.assertTrue(newsletterPage.isSubmitButtonDisplayed(), "Submit button should exist");
        Assertions.assertTrue(newsletterPage.isPageHeaderDisplayed(), "'Stay updated!' header should exist");
        Assertions.assertTrue(newsletterPage.isPageImageDisplayed(), "Page image should exist");

        System.out.println("✅ Page elements test passed!");
    }

    @Test
    @DisplayName("Test Invalid Email Validation Only")
    public void testInvalidEmailValidation() {
        System.out.println("🚀 Testing invalid email validation");

        navigateToHomePage();
        NewsletterPage newsletterPage = new NewsletterPage(driver);

        // Enter invalid email and verify error
        String invalidEmail = "invalid-email";
        newsletterPage.subscribeWithEmail(invalidEmail);

        // Wait for error message
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assertions.assertTrue(newsletterPage.isErrorMessageDisplayed(),
                "Error message should appear for invalid email");

        System.out.println("✅ Invalid email validation test passed!");
    }
}
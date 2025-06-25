package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.io.File;

public class BaseTest {
    protected WebDriver driver;
    protected final String BASE_URL = "https://newsletter-signup-form.vercel.app/";

    @BeforeEach
    public void setUp() {
        System.out.println("🚀 Starting browser setup...");

        // Set the path to your ChromeDriver executable
        String chromeDriverPath = System.getProperty("webdriver.chrome.driver","resources/chromedriver.exe");

        System.out.println("🔍 Looking for ChromeDriver at: " + chromeDriverPath);

        // Check if ChromeDriver exists
        File chromeDriverFile = new File(chromeDriverPath);
        if (!chromeDriverFile.exists()) {
            System.err.println("❌ ChromeDriver not found at: " + chromeDriverPath);
            System.err.println("💡 Make sure chromedriver.exe is in the resources folder");
            throw new RuntimeException("ChromeDriver executable not found");
        }

        // Set the ChromeDriver system property
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        System.out.println("✅ ChromeDriver path set successfully");

        try {
            // Initialize driver
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();


            System.out.println("✅ Browser setup completed successfully");
            System.out.println("🌐 Chrome version: " + ((ChromeDriver) driver).getCapabilities().getCapability("browserVersion"));
        } catch (Exception e) {
            System.err.println("❌ Failed to initialize Chrome driver: " + e.getMessage());
            System.err.println("💡 Make sure Chrome browser is installed and ChromeDriver version matches Chrome version");
            throw new RuntimeException("Browser setup failed", e);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                System.out.println("🔄 Closing browser...");
                driver.quit();
                System.out.println("✅ Browser closed successfully");
            } catch (Exception e) {
                System.err.println("⚠️ Error while closing browser: " + e.getMessage());
            }
        }
    }

    protected void navigateToHomePage() {
        System.out.println("🌐 Navigating to home page...");
        try {
            driver.get(BASE_URL);
            System.out.println("📍 Navigated to: " + BASE_URL);
            System.out.println("📄 Page title: " + driver.getTitle());
        } catch (Exception e) {
            System.err.println("❌ Failed to navigate to home page: " + e.getMessage());
            throw e;
        }
    }
}
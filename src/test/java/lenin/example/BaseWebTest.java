package lenin.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseWebTest {
    protected WebDriver webDriver;

    /**
     *Настраиваем окна и подключаем WebDriverManager
     * с указанием запускаться перед каждым тестом
     **/
    @BeforeEach
    void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().window().maximize();
    }

    /**
     *Закрывание браузера после завершения каждого теста
     **/
    @AfterEach
    void tearDown() {
        webDriver.quit();
    }
}

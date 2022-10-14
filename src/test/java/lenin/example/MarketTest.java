package lenin.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MarketTest extends BaseWebTest {

    @Test
     void marketYandexTest() throws Exception {

        /**
         * Указываем сайт для теста
         **/
        webDriver.get("https://dzen.ru/");

        /**
         * Указываем ожидание появления элемента
         **/
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions builder = new Actions(this.webDriver);

        /**
         *@Search Вбиваем поиск и критерии
         **/
        String Search = "Сотовые телефоны";
        String Search1 = "20000";
        String Search2 = "3";

        /**
         * Закрывает банер "Установаить"
         **/
        webDriver.findElement(By.xpath("//*[@fill='inherit']")).click();

        /**
         * Переключаемся на iframe и производим поиск
         **/
        WebElement iframe = webDriver.findElement(By.xpath("//iframe[@class='dzen-search-arrow-common__frame']"));
        webDriver.switchTo().frame(iframe);
        webDriver.findElement(By.xpath("//input[@name='text']")).sendKeys(Search);
        webDriver.findElement(By.xpath("//button[@class='arrow__button']")).click();
        webDriver.switchTo().defaultContent();

        /**
         *Переход в новое окно для тестирования маркета
         **/
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        webDriver.get("https://yandex.ru/products/search?from=tabbar&text=Сотовые+телефоны");
        SnapShot.takeSnapShot(webDriver, "SnapShot/Test Task/1.Переход в новое окно для тестирования маркета.png");

        /**
         *Заходим в расширенный поиск указываем необходимые параметры
         * 5 производителей не удалось указать
         **/
        webDriver.findElement(By.id("filter")).click();
        webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/section/ul/li[2]/button")).click();
        webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/section/div[2]/div[1]/div[1]/span[2]/input")).sendKeys(Search1);
        webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/section/div[2]/div[2]/button[2]")).click();

        webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/section/ul/li[6]/button")).click();
        webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/section/div[2]/div[1]/div/span[1]/input")).sendKeys(Search2);
        webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/section/div[2]/div[2]/button[2]")).click();

        webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/section/ul/li[1]/button")).click();
        WebElement element = webDriver.findElement(By.xpath("//label[@title='Alcatel']//input[@type='checkbox']"));
        builder.moveToElement(element);
        builder.click();
        builder.build().perform();

        WebElement element1 = webDriver.findElement(By.xpath("//label[@title='Blackview']//input[@type='checkbox']"));
        builder.moveToElement(element1);
        builder.click();
        builder.build().perform();

        WebElement element2 = webDriver.findElement(By.xpath("//label[@title='BQ']//input[@type='checkbox']"));
        builder.moveToElement(element2);
        builder.click();
        builder.build().perform();
       SnapShot.takeSnapShot(webDriver, "SnapShot/Test Task/2.Расширенный поиск.png");

        webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/section/div[2]/button[2]")).click();

        /**
         *Очистка поискового запроса
         **/
        webDriver.findElement(By.id("uniq16656243351631")).clear();
       SnapShot.takeSnapShot(webDriver, "SnapShot/Test Task/3.Очистка поискового запроса.png");
        /**
         *Проверяем что элементов на странице 10
         **/
        int result = webDriver.findElements(By.xpath("//div[@class='ProductCardsList-Item']")).size();
        if (result <= 10) {
            throw new ArithmeticException("На странице менее 10 элементов");
        } else {
            System.out.println("На странице более 10 элементов");
        }

        /**
         *Запоминем первый элемент в списке
         **/
        List<WebElement> results = webDriver.findElements(By.xpath("//div[@class='ProductCardsList-Item']"));
        WebElement list = results.get(1);
        String product = list.findElement(By.xpath("//div[@class='ProductCard-Title']")).getText();
        System.out.println(product);

        /**
         *Меняем сотировку на "Сначала недорогие"
         **/
        WebElement element6 = webDriver.findElement(By.id("sorting"));
        builder.moveToElement(element6);
        builder.click();
        builder.build().perform();
        webDriver.findElement(By.xpath("//div[@class='ProductListDropDown-SelectWrapper ProductListSorting']//button[text()=\"Сначала недорогие\"]")).click();
        SnapShot.takeSnapShot(webDriver, "SnapShot/Test Task/4.Меняем сотировку на Сначала недорогие.png");

        /**
         *Находим и нажимаем по имени запомненого объекта
         **/
        webDriver.findElement(By.id("uniq16656243351631")).click();
        webDriver.findElement(By.id("uniq16656243351631")).sendKeys(product);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/header/div/div[1]/div[2]/form/div[2]/button")).click();
        List<WebElement> resultss = webDriver.findElements(By.xpath("//div[@class='ProductCardsList-Item']"));
        resultss.get(1).click();
        SnapShot.takeSnapShot(webDriver, "SnapShot/Test Task/5.Находим и нажимаем по имени запомненого объекта.png");

        /**
         *Выводим цифровое значение оценки если оно есть
         **/
        try {
            String score = webDriver.findElement(By.xpath("//div[@class='Rating Rating_category_good RatingLine-Rating']")).getText();
            System.out.println("Оценка товара: " + score);
        } catch (NoSuchElementException ex) {
            System.out.println("Оценка не указана");
        }
    }
}





// все какие-то методы/локаторы, будем хранить на этой стр.
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    public WebDriverWait wait; //ОСТАВЛЯТЬ ИЛИ НЕТ
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/"; //константа

    //здесь модификатор private или все-таки public ?!
    public By cookieQuestion = By.className("App_CookieConsent__1yUIN");
    public By cookieAcceptButton = By.id("rcc-confirm-button"); //кнопка принятия куки
    public By orderButtonTop = By.xpath("(//button[text()='Заказать'])[1]"); //локатор заказать вверху
    public By orderButtonBelow = By.xpath("(//button[text()='Заказать'])[2]"); //локатор заказать внизу

    public MainPage(WebDriver driver) {
        System.out.println("MainPage конструктор вызван");
        System.out.println("Получен драйвер: " + (driver != null));
        this.driver=driver;
        System.out.println("this.driver установлен: " + (this.driver != null));
    }

    public void scrollToElement(By locator) { // ДОБАВИЛА ОТДЕЛЬНЫЙ МЕТОД ДЛЯ СКРОЛЛА
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void acceptCookiesIfPresent() {
        try {
            // Ждем появления окна с куки (до 5 секунд)
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(cookieQuestion));

            // Если окно появилось - кликаем на кнопку принятия куки
            driver.findElement(cookieAcceptButton).click();

            // Ждем скрытия окна с куки
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieQuestion));
        } catch (TimeoutException e) {
            // Если окно с куки не появилось за 5 секунд - ничего не делаем
            System.out.println("Окно с куки не появилось, продолжаем тест");
        }
    }

    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonTop).click(); // метод для верхей кнопки ЗАКАЗАТЬ
    }

    public void setOrderButtonBelow() {
        scrollToElement(orderButtonBelow);// ЗДЕСЬ ВЫЗЫВАЮ СКРОЛЛ
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBelow));
        driver.findElement(orderButtonBelow).click();
    } // метод для нижней кнопки ЗАКАЗАТЬ

    public void openPage() {
        driver.get(BASE_URL); //НАДО ВЫНЕСТИ В КОНСТАНТУ!!!!!
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Заказать']")));
    }
}
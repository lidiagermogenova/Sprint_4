package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutRentPage {
    private WebDriver driver;
    private By orderDate = By.xpath(".//input[@placeholder = '* Когда привезти самокат']"); // поле "Когда привезти самокат";
    private By rentalPeriod = By.xpath(".//div[@class = 'Dropdown-control']"); // поле "Срок аренды";
    private By scooterColorBlack = By.xpath(".//label[@for = 'black']"); // выбор цвета "Чёрный жемчуг";
    private By scooterColorGrey = By.xpath(".//label[@for = 'grey']"); // выбор цвета "Серая безысходность";
    private By commentField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']"); // поле "Комментарий для курьера";
    private By confirmOrderButton = By.xpath("(//button[text() = 'Заказать'])[2]"); // кнопка ЗАКАЗАТЬ
    private By orderConfirmationField = By.xpath("//div[contains(@class, 'Order_Modal')]"); // поле подтверждения заказа "НЕТ/ДА";
    private By confirmButtonYes = By.xpath("//button[text() = 'Да']"); // кнопка "ДА";
    private By orderAcceptHeader = By.xpath("//div[contains(@class,'Order_ModalHeader') and text()='Заказ оформлен']"); //ПОСЛЕ НАЖАТИЯ ВЫВОДИТСЯ ТЕКСТ С ПОДТВЕРЖДЕНИЕМ ЗАКАЗА, ЭТО МЫ УЗНАЛИ ИЗ ФФ
    private By calendarDay(String day) {
        return By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']");
    }

    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setOrderDate(String day) {
        driver.findElement(orderDate).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(calendarDay(day)));
        driver.findElement(calendarDay(day)).click();
    }

    public void setRentalPeriod(String period) {
        driver.findElement(rentalPeriod).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By periodList = By.xpath(String.format("//div[contains(@Class,'Dropdown-option') and (text()='%s')]", period)); // убрала конкатенацию и выбрала String.format();
        wait.until(ExpectedConditions.visibilityOfElementLocated(periodList));
        driver.findElement(periodList).click();
    }

    public void setScooterColorBlack() {

        driver.findElement(scooterColorBlack).click();
    }
    public void setScooterColorGrey() {

        driver.findElement(scooterColorGrey).click();
    }

    public void setCommentField(String comment) {

        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() { //нажать кнопку ЗАКАЗАТЬ

        driver.findElement(confirmOrderButton).click();
    }

    public void clickConfirmOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationField));
        wait.until(ExpectedConditions.elementToBeClickable(confirmButtonYes));
        driver.findElement(confirmButtonYes).click();
    }

    public boolean isOrderAccept() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(orderAcceptHeader));
        return successMessage.isDisplayed();
    }

    public void fillAboutRent(String day, String period, String color, String comment) {
        setOrderDate(day);

        setRentalPeriod(period);

        if ("black".equals(color)) {
            setScooterColorBlack();
        } else if ("grey".equals(color)) {
            setScooterColorGrey();
        }

        setCommentField(comment);
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerInformation {
    private WebDriver driver;

    private By setName = By.cssSelector("input[placeholder='* Имя']"); // поле "Имя";
    private By setLastName = By.cssSelector("input[placeholder='* Фамилия']"); // поле "Фамилия";
    private By setAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']"); // поле "Адрес";
    private By setPhoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']"); // поле "Номер телефона";
    private By stationField = By.xpath("//input[@placeholder='* Станция метро']"); // поле "Станция метро";
    private By clickNextButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Далее']"); // кнопка "Далее";

    public CustomerInformation(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(setName).sendKeys(name);
    }
    public void setLastName(String lastName) {
        driver.findElement(setLastName).sendKeys(lastName);
    }
    public void setAddress(String address) {
        driver.findElement(setAddress).sendKeys(address);
    }

    public void getMetroField(String station, String fullStationName) {
        driver.findElement(stationField).sendKeys(station);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By option = By.xpath("//button[contains(@class,'select-search__option') and contains(.,'" + fullStationName + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(option));
        driver.findElement(option).click();
    }

    public void setPhoneNumber(String number) {
        driver.findElement(setPhoneNumber).sendKeys(number);
    }
    public void clickNextButton() {
        driver.findElement(clickNextButton).click();
    }

    public void fillUserInfoPage(String name, String lastName, String address, String stationShort, String stationFull, String number) {
        setName(name);
        setLastName(lastName);
        setAddress(address);
        getMetroField(stationShort, stationFull);
        setPhoneNumber(number);
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class FAQPage {
    private WebDriver driver;

    private By nameFaq = By.xpath("//div[text() = 'Вопросы о важном']");
    private By questions = By.className("accordion__button");
    private By answers = By.className("accordion__panel");

    public FAQPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollFaqSection() {
        WebElement element = driver.findElement(nameFaq);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameFaq));
    }

    public List<WebElement> getAllQuestions() {
        return driver.findElements(questions);
    }

    public List<WebElement> getAllAnswers() {
        return driver.findElements(answers);
    }

    public void clickQuestion(int index) {
        List<WebElement> questionsList = getAllQuestions();
        List<WebElement> answersList = getAllAnswers();
        questionsList.get(index).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(answersList.get(index)));
    }
}
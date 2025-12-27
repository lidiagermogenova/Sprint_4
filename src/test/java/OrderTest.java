import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String name;
    private final String lastName;
    private final String address;
    private final String stationShort;
    private final String stationFull;
    private final String number;
    private final String day;
    private final String period;
    private final String color;
    private final String comment;
    private final String buttonType;

    public OrderTest (String name, String lastName, String address, String stationShort, String stationFull, String number, String day, String period, String color, String comment, String buttonType) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.stationShort = stationShort;
        this.stationFull = stationFull;
        this.number = number;
        this.day = day;
        this.period = period;
        this.color = color;
        this.comment = comment;
        this.buttonType = buttonType;
    }

    @Parameterized.Parameters
    public static Object[][] RentalData() {
        return new Object[][]{
                {"Яна", "Иванова", "проспект Королева 10", "Преоб", "Преображенская площадь", "+79998886622", "28", "трое суток", "black", "Позвонить заранее", "header"},
                {"Лида", "Канарейкина", "Невская 15", "Черкиз", "Черкизовская", "89556734411", "18", "сутки", "grey", "Ожидаю в 10", "bottom"}
        };
    }

    @Test
    public void forWhom() {
        // Открыть сайт;
        mainPage.openPage();
        System.out.println("1. Страница открыта");
        // Ожидаем загрузки страницы;
        try {
            System.out.println("2. Ждём 2 секунды...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Принять куки;
        mainPage.acceptCookiesIfPresent();
        System.out.println("3. Куки приняты");
        // Нажать кнопку "Заказать";
        if ("header".equals(buttonType)) {
            mainPage.clickOrderButtonHeader();
            System.out.println("4. Нажата верхняя кнопка 'Заказать'");
        } else if ("bottom".equals(buttonType)) {
            mainPage.setOrderButtonBelow();
            System.out.println("4. Нажата нижняя кнопка 'Заказать'");
        }
        // Заполнить данные о пользователе;
        customerInformation.fillUserInfoPage(name, lastName, address, stationShort, stationFull, number);
        System.out.println("5. Данные пользователя заполнены");
        // Нажать кнопку "Далее";
        customerInformation.clickNextButton();
        System.out.println("6. Нажата кнопка 'Далее'");
        // Ввести данные об аренде;
        aboutRent.fillAboutRent(day, period, color, comment);
        System.out.println("7. Данные аренды заполнены");
        // Нажать кнопку "Заказать";
        aboutRent.clickOrderButton();
        System.out.println("8. Нажата кнопка 'Заказать'");
        // Подтвердить заказ "Да";
        aboutRent.clickConfirmOrderButton();
        System.out.println("9. Подтверждён заказ 'Да'");
        // Сравнить ОР(заказ оформлен) и ФР
        boolean isAccepted = aboutRent.isOrderAccept();
        System.out.println("10. Заказ принят: " + isAccepted);

        Assert.assertTrue("Сообщение об успешном оформлении заказа не отображается", aboutRent.isOrderAccept());
        System.out.println("ТЕСТ ЗАВЕРШЁН");
    }
}
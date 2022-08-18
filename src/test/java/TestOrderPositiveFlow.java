import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.example.ArendaPage;
import org.example.ForWhomSamokatPage;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.List;

public class TestOrderPositiveFlow {
    private WebDriver driver;
    String name;
    String surname;
    String address;
    String stationNumber;
    String phoneNumber;
    String day;
    String period;
    String color;
    String commentary;

 /*   public TestOrderPositiveFlow(String name, String surname, String address, String stationNumber, String phoneNumber, String day, String period, String color, String commentary) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationNumber = stationNumber;
        this.phoneNumber = phoneNumber;
        this.day = day;
        this.period = period;
        this.color = color;
        this.commentary = commentary;
    }
*/

    public static List<Object[]> getTextData() {
        return Arrays.asList( new Object[][]{
                {"Алла", "Алексеев", "Москва", "2", "+79289472811", "003", "двое суток", "black", "Тест"},
                {"Кирилл", "Михайлович", "Курская", "5", "+79289765456", "006", "пятеро суток", "grey", "Коммент"},
        });
    }


    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void orderPositiveFlow() {
        String expected = "Заказ оформлен";
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
        ForWhomSamokatPage objForWhomSamokatPage = new ForWhomSamokatPage(driver);
        objForWhomSamokatPage.forWhomSamokatFilledUp("Шарип", "Алексеев",
                "Москва", "2","+79289472811");
        ArendaPage objArendaPage = new ArendaPage(driver);
        objArendaPage.arendaPagetFilled("003", "двое суток", "black", "Тест");
        String actual = objArendaPage.getSuccessMessage();
        Assert.assertTrue(actual.contains(expected));
    }

    @Test
    public void orderPositiveFlow2() {
        String expected = "Заказ оформлен";
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
        ForWhomSamokatPage objForWhomSamokatPage = new ForWhomSamokatPage(driver);
        objForWhomSamokatPage.forWhomSamokatFilledLow("Антипа", "Михайлович",
                "Курская", "5", "+79289765456");
        ArendaPage objArendaPage = new ArendaPage(driver);
        objArendaPage.arendaPagetFilled("006", "пятеро суток", "grey", "Коммент");
        String actual = objArendaPage.getSuccessMessage();
        Assert.assertTrue(actual.contains(expected));
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}

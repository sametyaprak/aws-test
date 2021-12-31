
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;



public class HomePage2Test extends TestBase {



    @org.junit.Test
    @DisplayName("TC002001")
    @EnabledOnOs(OS.LINUX)
    public void OpenBrowser1()  {

        DriverFactorySingleton.getInstance().getDriver().get("https://www.google.com");
//        Assertions.assertEquals("Kitapyurdu, Kitapla buluşmanın en kolay yolu",
//                DriverFactorySingleton.getInstance().getDriver().getTitle(),
//                "Page title is not correct");
//        actions.sendKeys(Keys.ENTER).perform();
    }

    }








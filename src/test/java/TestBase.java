import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

    protected Actions actions = new Actions(DriverFactorySingleton.getInstance().getDriver());
    private static final Logger LOG = LoggerFactory.getLogger(TestBase.class);


    @Before
    public void before ( ) {
        LOG.info("before method initialized..  ***  " + Thread.currentThread().getId());
        //DriverFactorySingleton.getInstance().getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }


    @After
    public void tearDown ( ) throws InterruptedException {
        LOG.info("tear down method worked..  ***  " + Thread.currentThread().getId());
        DriverFactorySingleton.getInstance().getDriver().close();
        Thread.sleep(1000);
    }


}

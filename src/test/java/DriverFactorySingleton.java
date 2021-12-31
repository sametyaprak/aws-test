import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DriverFactorySingleton {

    private final static ThreadLocal<DriverFactorySingleton> driverFactorySingleton = new ThreadLocal<>();
    private final static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static final Logger LOG = LoggerFactory.getLogger(DriverFactorySingleton.class);


    private static final String BROWSER_TYPE = "aws";
    private static final boolean HEADLESS_MODE = false;
    private static final boolean FULLSCREEN = false;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;


    private DriverFactorySingleton() {
        LOG.trace("Intantiated singleton class....  ***  " + Thread.currentThread().getId());
    }

    public static DriverFactorySingleton getInstance(){
        LOG.trace("New DriverFactorySingelton instance initiated....  ***  " + Thread.currentThread().getId());
        if(driverFactorySingleton.get()==null)
        {
            driverFactorySingleton.set(new DriverFactorySingleton());
            setDriver();
        }
        return driverFactorySingleton.get();
    }

    private static void setDriver(){
        LOG.trace("Web driver is set...  ***  " + Thread.currentThread().getId());
        switch (BROWSER_TYPE){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(HEADLESS_MODE){
                    firefoxOptions.addArguments("headless");
                    firefoxOptions.addArguments("disable-gpu");
                    firefoxOptions.addArguments("--no-sandbox");
                    webDriver.set(new FirefoxDriver(firefoxOptions));
                    break;
                }
                FirefoxDriver firefoxDriver = new FirefoxDriver();
                if(FULLSCREEN){
                    firefoxDriver.manage().window().maximize();
                } else {
                    Dimension dimension = new Dimension(WIDTH, HEIGHT);
                    firefoxDriver.manage().window().setSize(dimension);
                }
                webDriver.set(firefoxDriver);
                break;
            case "ie":
                webDriver.set(new InternetExplorerDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                if(HEADLESS_MODE){
                    EdgeOptions options = new EdgeOptions();
                    webDriver.set(new EdgeDriver(options));
                } else {
                    webDriver.set(new EdgeDriver());
                }
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                OperaDriver driver = new OperaDriver();
                if(FULLSCREEN){
                    driver.manage().window().maximize();
                } else {
                    Dimension dimension = new Dimension(WIDTH, HEIGHT);
                    driver.manage().window().setSize(dimension);
                }
                webDriver.set(driver);
                break;
            case "aws":
                System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
                ChromeOptions optionsAWS = new ChromeOptions();
                optionsAWS.addArguments("disable-gpu");
                optionsAWS.setHeadless(true);
                optionsAWS.addArguments("--no-sandbox");
                optionsAWS.addArguments("headless");
                optionsAWS.addArguments("--disable-dev-shm-usage");
                optionsAWS.addArguments("--disable-extensions");
                optionsAWS.addArguments("--allow-insecure-localhost");
                optionsAWS.addArguments("--single-process");
                webDriver.set(new ChromeDriver(optionsAWS));
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:/Users/Administrator/Documents/seleniumdependencies/chromedriver.exe");
                //WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if(HEADLESS_MODE){
                    options.addArguments("headless");
                    options.addArguments("disable-gpu");
                    options.addArguments("--no-sandbox");
                    webDriver.set(new ChromeDriver(options));
                    break;
                }
                ChromeDriver chromeDriver = new ChromeDriver();
                if(FULLSCREEN){
                    chromeDriver.manage().window().maximize();
                } else {
                    Dimension dimension = new Dimension(WIDTH, HEIGHT);
                    chromeDriver.manage().window().setSize(dimension);
                }
                webDriver.set(chromeDriver);
                break;



        }
        //System.out.println("Driver intialzed by "+Thread.currentThread().getId()+" with reference as "+ webDriver.get());
    }

    public WebDriver getDriver(){
        LOG.trace("getDriver() initialized...    ***  " + Thread.currentThread().getId());
        return webDriver.get();

    }

    public void removeDriver(){
        LOG.trace("removeDriver() initialized...");
        webDriver.get().close();
        driverFactorySingleton.remove();
        webDriver.remove();
    }


}

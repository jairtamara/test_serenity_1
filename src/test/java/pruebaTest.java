import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class pruebaTest {
    WebDriver driver;

    @Before
    public void lanzarDriver() {


        //buscar el exe del navegador
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        // ChromeOptions lo usamos para configuraciones que deseamos que tenga  el navedor
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");  // Ejecutar sin interfaz gráfica
        //options.addArguments("--disable-gpu");  // Deshabilitar la aceleración de GPU
        //options.addArguments("--incognito");  // Modo incógnito
        //options.addArguments("--start-maximized");  // Iniciar maximizado
        options.addArguments("--disable-blink-features=AutomationControlled");  // Evita detección de Selenium
        // instaccian de navegador chrome
        driver = new ChromeDriver(options);

    }

    @Test
    public void hacer_una_busqueda() {

        // dirigir a la URL
        driver.get("https://www.google.com/?hl=es");

        // encontrar elemento
        driver.findElement(By.xpath("(//textarea[@id='APjFqb'])[1]")).sendKeys("el tiempo");
        // dar click
        driver.findElement(By.xpath("(//textarea[@id='APjFqb'])[1]")).sendKeys(Keys.ENTER);

        // ingresar al segundo link de la busqueda
        driver.findElement(By.xpath("//h3[contains(text(),'ELTIEMPO.COM: Últimas noticias de Colombia y el Mu')]")).click();

        //crear un elemento web
        WebElement cadena = driver.findElement(By.xpath("//a[normalize-space()='Catatumbo']"));

        try {
            String text = cadena.getText();
            // validar la existencia de la palabra "Catatumbo"
            Assert.assertTrue(text.contains("Catatumbo"));
            System.out.println("el texto " + text + " si esta ");

        } catch (AssertionError e) {
            System.out.println("Error: ->  " + e.getMessage());

            String error = e.getMessage();
            System.out.println("Validar el log " + error);
            driver.quit();
            // marca como fallido el test "throw" sfgs fdg
            throw e;

        }
    }

    @After
    public void cerrarNavegador() {
        driver.quit();
    }


}

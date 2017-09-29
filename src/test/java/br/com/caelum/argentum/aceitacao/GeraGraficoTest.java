package br.com.caelum.argentum.aceitacao;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GeraGraficoTest {
	private static final String URL = 
			"http://localhost:8080/fj22-argentum-web/index.xhtml";
	private WebDriver driver;
	
	@Before
	public void setUp() {
		String exePath = "chromedriver";
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
	}
	
	@Test
	public void testeAoGerarGraficoSemTituloUmaMensagemEhApresentada() {
		driver.navigate().to(URL);
		
		WebElement titulo = driver.findElement(By.id("dadosGrafico:titulo"));
		titulo.clear();
		
		WebElement gerarGraficoBtn = driver.findElement(By.id("dadosGrafico:gerarGraficoBtn"));
		gerarGraficoBtn.submit();
		
		String pageSource = driver.getPageSource();
		boolean existeMensagem = pageSource.contains("Validation Error");
		System.out.println(pageSource);
		assertTrue(existeMensagem);
		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
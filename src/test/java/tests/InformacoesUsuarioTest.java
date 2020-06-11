package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.Assert.*;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {
	// atributo da classe
	private WebDriver driver;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.juliodelima.com.br/taskit");
		driver.manage().window().maximize();
	}

	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario() {

		driver.findElement(By.linkText("Sign in")).click();
		// busca pelo contexto
		// signbox possui login e password
		WebElement formularioSignBox = driver.findElement(By.id("signinbox"));

		formularioSignBox.findElement(By.name("login")).sendKeys("julio0001");
		formularioSignBox.findElement(By.name("password")).sendKeys("123456");

		driver.findElement(By.linkText("SIGN IN")).click();

		WebElement me = driver.findElement(By.className("me"));

		String textoNoElementoMe = me.getText();

		// validação
		// espera que o texto 'Hi, Julio'- esteja no elemento 'me'
		assertEquals("Hi, Julio", textoNoElementoMe);
	}

	@After
	public void tearDown() {

		driver.quit();
	}
}

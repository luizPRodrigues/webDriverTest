package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.LoginPage;
import suporte.Web;

public class InformacoesUsuarioPageObjectsTest {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		driver = Web.createChrome();
		
	}
	
	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
		new LoginPage(driver)
		         .clickSignIn()
		         .digitarLogin("julio0001")
		         .digitarSenha("123456")
		         .clicarSignIn()
		         .clicarMe()
		         .clicarNaAbaMoreDataAboutYou()
		         .clicarNoBotãoAddMorDataAboutYou();
	}
	
	@After
	public void tearDown() {
		//driver.quit();
		
	}

}

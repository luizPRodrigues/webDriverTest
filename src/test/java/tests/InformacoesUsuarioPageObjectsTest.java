package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import pages.LoginPage;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv")
public class InformacoesUsuarioPageObjectsTest {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		driver = Web.createChrome();
		
	}
	
	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario(
			@Param(name="login")String login,
		    @Param(name="senha")String senha,
		    @Param(name="tipo")String tipo,
		    @Param(name="contato")String contato,
		    @Param(name="mensagem")String mensagem
	) {		
		String textoToast = new LoginPage(driver)
		    .clickSignIn()
		    .fazerLogin(login, senha)
		    .clicarMe()
		    .clicarNaAbaMoreDataAboutYou()
		    .clicarNoBotãoAddMoreDataAboutYou()
		    .adicionarContato(tipo, contato)
		    .capturarTextoToast();
		assertEquals(mensagem, textoToast);
		
	}
	
	@After
	public void tearDown() {
		driver.quit();
		
	}
}

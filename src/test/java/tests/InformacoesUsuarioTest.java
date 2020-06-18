package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import static junit.framework.Assert.*;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")

public class InformacoesUsuarioTest {

	// atributo da classe
	private WebDriver driver;

	@Rule
	public TestName test = new TestName();

	@Before
	public void setUp() {
		
		driver = Web.createChrome();
		
		driver.findElement(By.linkText("Sign in")).click();

		// busca pelo contexto
		// signbox possui login e password
		WebElement formularioSignBox = driver.findElement(By.id("signinbox"));

		formularioSignBox.findElement(By.name("login")).sendKeys("julio0001");
		formularioSignBox.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.linkText("SIGN IN")).click();

		// clicar em um link que possui a class "me"
		driver.findElement(By.className("me")).click();

		// clicar em um link que possui o texto "MORE DATA ABOUT YOU"
		driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
	}

	@Test                                                           //parametros que pegam os dados das colunas 
	public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) {

		// clicar no botao atravez do seu xpath
		driver.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

		// identificar a pop up aonde esta o formulario de id addmoredata
		WebElement popupAddMoreData = driver.findElement(By.id("addmoredata"));

		// Na combo de name "type" escoher a opção "phone"
		// Utilizar classe específica do selenium para comboBox
		// O new select cria opções para funções específicas- neste caso irá selecionar
		// um valor que é visível ao usuário
		WebElement campoType = popupAddMoreData.findElement(By.name("type"));
		new Select(campoType).selectByVisibleText(tipo);

		// no combo de name "contact" digitar "+5511923456789"
		popupAddMoreData.findElement(By.name("contact")).sendKeys(contato); 

		// clicar no link de text "save" que esta na pop up
		popupAddMoreData.findElement(By.linkText("SAVE")).click();

		// na mensagem de id "toastcontainer" validar que o texto é "Your contact has been added!"
		WebElement mensagemPopup = driver.findElement(By.id("toast-container"));
		String mensagem = mensagemPopup.getText();
		assertEquals(mensagemEsperada, mensagem);
		
		String screenshotArquivo = "C:\\Users\\luiz.rodrigues\\eclipse-workspace\\webdriverJava\\Test_Report\\taskit"
				+ Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
		Screenshot.takeShoot(driver, screenshotArquivo);
		
		// Clicar no link com texto "logout"
				driver.findElement(By.linkText("Logout")).click();
		

	}

	//@Test
	public void removerUmContatoDeUmUsuario() {
		// xpath complexo
		// identificou o elemento por texto- pega o proximo elemento após ele que seja
		// um 'a'
		// para pegar um elemento anterior a ele substituir o 'following' por-
		// 'preceding'

		// clicar no seu elemento pelo xpath
		// //span[text()='+551133334444']/following-sibling::a
		driver.findElement(By.xpath("//span[text()=\"+55111985\"]/following-sibling::a")).click();

		// Confirmar a janela javaScript
		// atravez do metodo switchTo é possível acessar a janela de alerta em Js e
		// escolher a ação desejada
		driver.switchTo().alert().accept();

		// validar que a mensagem apresentada foi 'Rest in peace, dear phone!'
		WebElement mensagemRIP = driver.findElement(By.id("toast-container"));
		String mensagem = mensagemRIP.getText();
		assertEquals("Rest in peace, dear phone!", mensagem);

		String screenshotArquivo = "C:\\Users\\luiz.rodrigues\\eclipse-workspace\\webdriverJava\\Test_Report\\taskit"
				+ Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
		Screenshot.takeShoot(driver, screenshotArquivo);

		// Aguardar até 10 sec. para qie a janela desapareça
		WebDriverWait aguardar = new WebDriverWait(driver, 10);
		// Espera que aguarda ate o popup Js desapareça da página
		aguardar.until(ExpectedConditions.stalenessOf(mensagemRIP));

		// Clicar no link com texto "logout"
		driver.findElement(By.linkText("Logout")).click();

	}

	@After
	public void tearDown() {

		driver.quit();
	}
}

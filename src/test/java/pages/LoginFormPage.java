package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {

	public LoginFormPage(WebDriver driver) {
		super(driver);
	}

	// MONTAGEM ESTRUTURAL ******** SENDO UM METODO PARA CADA STEP

	public LoginFormPage digitarLogin(String login) {
		driver.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
		return this;

	}

	public LoginFormPage digitarSenha(String password) {
		driver.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
		return this;
	}

	public SecretaPage clicarSignIn() {
		driver.findElement(By.linkText("SIGN IN")).click();
		return new SecretaPage(driver);
	}
   
	//METODO FUNCIONAL ********* ENCAPSULAMENTO DOS METODOS ESTRUTURAIS 
	public SecretaPage fazerLogin(String login, String senha) {
		digitarLogin(login);
		digitarSenha(senha);
		clicarSignIn();

		return new SecretaPage(driver);

	}
}

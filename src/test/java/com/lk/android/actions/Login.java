package com.lk.android.actions;

import com.lk.android.pages.Personal;
import com.lk.android.utils.AndroidDriverPlus;


public class Login {
	
	private AndroidDriverPlus driver;
	
	public Login(AndroidDriverPlus driver){
		this.driver = driver;
	}

	public void run(String account,String pwd){
		
		driver.findElement(Personal.account).sendKeys(account);
		driver.findElement(Personal.password).sendKeys(pwd);
		driver.findElement(Personal.loginBtn).click();
				
	}
	
}

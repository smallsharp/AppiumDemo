package com.lk.android.actions;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.lk.android.pages.HomePage;
import com.lk.android.utils.AndroidBaseCase;
import com.lk.android.utils.AndroidDriverPlus;

public class Welcome {

	private static AndroidDriverPlus ad = AndroidBaseCase.getDriver();
	
	public void run() throws InterruptedException {
				
		boolean result = false;
		
		if (ad.findElementById(HomePage.logo).isDisplayed()) {
			System.out.println("find the logo");
			ad.swipeToLeft(3);// 左滑三次
			
			ad.findElement(By.name("立即开启")).click();
			ad.findElement(By.name("首页")).click();
			ad.findElement(By.name("首页")).click();			

			if (ad.findElement(By.name("附近")).isDisplayed()) {
				result = true;
			}
		}
		Assert.assertTrue(result);
	}

}
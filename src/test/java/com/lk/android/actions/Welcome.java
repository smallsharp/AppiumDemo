package com.lk.android.actions;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.lk.android.common.Activity;
import com.lk.android.pages.HomePage;
import com.lk.android.utils.AndroidDriverPlus;

public class Welcome {

	private static AndroidDriverPlus ad;

	public Welcome(AndroidDriverPlus adPlus) {
		Welcome.ad = adPlus;
	}

	public void run() throws InterruptedException {
		
		boolean result = false;

		if (ad.currentActivity().equals(Activity.SplashActivity)
				&& ad.findElementById(HomePage.logo).isDisplayed()) {
			ad.swipeToLeft(3);// 左滑三次
			ad.findElement(By.name("立即开启")).click();
			
			ad.findElement(By.name("签到")).click();
			ad.findElement(By.name("首页")).click();			

			if (ad.findElement(By.name("我")).isDisplayed()) {
				result = true;
			}
		}
		Assert.assertTrue(result);

	}

}
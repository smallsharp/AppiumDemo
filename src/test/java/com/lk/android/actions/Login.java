package com.lk.android.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.lk.android.pages.Personal;
import com.lk.android.utils.AndroidBaseCase;
import com.lk.android.utils.AndroidDriverPlus;

public class Login {

	private AndroidDriverPlus ad = AndroidBaseCase.getDriver();

	private Logger logger = Logger.getLogger(Login.class);
	
	public Login(){
		
	}

	public Login(AndroidDriverPlus driver) {
		this.ad = driver;
	}

	public void loginSuccess(String account, String pwd) {

		boolean result = false;
		// 前提是没有登录
		if (ad.findElement(By.name("点击登录")).isDisplayed()) {

			ad.findElement(By.name("点击登录")).click();
			ad.findElement(Personal.account).sendKeys(account);
			ad.findElement(Personal.password).sendKeys(pwd);
			ad.findElement(Personal.loginBtn).click();

			// 如果出现用户昵称，则判断登录成功
			if (ad.findElement(By.id("com.scienvo.app.troadon:id/user_name")).isDisplayed()) {
				result = true;
			}
		}

		Assert.assertTrue(result);
	}

	public void loginFailed(String account, String pwd) {

		boolean result = false;

		// 前提是没有登录
		if (ad.findElement(By.name("点击登录")).isDisplayed()) {

			ad.findElement(By.name("点击登录")).click();
			ad.findElement(Personal.account).sendKeys(account);
			ad.findElement(Personal.password).sendKeys(pwd);
			ad.findElement(Personal.loginBtn).click();

			// 错误的账号密码应该登录不成功，如果登录成功，则页面跳转到main
			if (!ad.currentActivity().equals(".MainActivity")) {
				result = true;
			}
		} else {
			logger.error("没有找到：点击登录");
		}

		Assert.assertTrue(result);
	}

	public void loginOut() {

		boolean result = false;

		// 登录状态下才能退出
		if (ad.findElement(By.id("com.scienvo.app.troadon:id/user_name")).isDisplayed()) {

			ad.scrollTo("设置");
			ad.findElement(By.name("设置")).click();
			ad.findElement(By.name("退出登录")).click();
			ad.findElement(By.name("是")).click();

			if (ad.findElement(By.name("点击登录")).isDisplayed()) {
				result = true;
			} else {
				logger.error("没有找到：点击登录");
			}

		} else {
			logger.error("没有找到：昵称");
		}

		Assert.assertTrue(result);
	}

}

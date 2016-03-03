package com.lk.android.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.lk.android.common.Activity;
import com.lk.android.utils.AndroidDriverPlus;

public class User {

	private AndroidDriverPlus ad;
	
	public User(AndroidDriverPlus ad){
		this.ad = ad;
	}

	List<WebElement> list = null;

	@SuppressWarnings("unchecked")
	public void editNickName() throws InterruptedException {
		boolean result = false;

		WebElement userlink = ad.findElement(By.name("个人信息"));
		userlink.click();

		list = ad.findElementsById("com.scienvo.app.troadon:id/value");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String currentTime = sdf.format(new Date());

		if (ad.currentActivity().equals(Activity.EditUserInfoActivity)) {
//			System.out.println("list:" + list.size());

			WebElement nick = list.get(0);// 昵称
			ad.clearText(nick);
			nick.sendKeys("我是一个粉刷匠" + currentTime);

			ad.findElement(By.id("com.scienvo.app.troadon:id/btn_nav_right")).click();// 点击：保存

			Thread.sleep(3000);
			System.out.println("currentActivity:" + ad.currentActivity());
			if (ad.currentActivity().equals(".MainActivity")) {
				result = true;
			}
		}

		Assert.assertTrue(result);
	}

	public void editMail() throws InterruptedException {

		boolean result = false;

		WebElement userlink = ad.findElement(By.name("个人信息"));
		userlink.click();
		if (ad.currentActivity().equals("com.scienvo.app.module.me.EditUserInfoActivity")) {
			WebElement mail = list.get(1);// 邮箱
			ad.clearText(mail);

			mail.sendKeys("5555@6666.com");
			ad.findElement(By.id("com.scienvo.app.troadon:id/btn_nav_right")).click();// 点击：保存
			Thread.sleep(3000);
			
			if (ad.currentActivity().equals(".MainActivity")) {
				result = true;
			}
		}

		Assert.assertTrue(result);
	}

}

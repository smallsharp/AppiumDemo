package com.lk.android.test;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lk.android.pages.Personal;
import com.lk.android.utils.AndroidInit;
import com.lk.android.utils.DataOffer;

public class TestCase2 extends AndroidInit{
	
	public DataOffer offer;
	
	@BeforeClass
	public void initTestData() throws MalformedURLException{
		offer = new DataOffer(System.getProperty("user.dir")+"/assets/data.csv");
	}
	
	@Test
	public void a_welcome() throws InterruptedException {
		System.out.println("开始执行：a_welcome()");
		Thread.sleep(5000);
		swipeToLeft();
		ad.findElement(By.name("立即开启")).click();
		ad.findElement(By.name("我")).click();
		ad.findElement(By.name("我")).click();
		ad.findElement(By.name("我")).click();
		ad.findElement(By.name("点击登录")).click();
		Thread.sleep(2000);
	}
	
	@Test
	public void t_login(){
		System.out.println("开始执行：login()");
		
		WebElement acc = ad.findElement(Personal.account);// 账号
		acc.sendKeys(offer.getData("account", "tc1"));
		
		WebElement pwd = ad.findElement(Personal.password);// 密码
		pwd.sendKeys(offer.getData("password", "tc1"));
		
		WebElement loginBtn = ad.findElement(Personal.loginBtn);// 点击登录
		loginBtn.click();

	}
	
	@Test
	public void t_login2(){
		System.out.println("开始执行：login()");
		
		WebElement acc = ad.findElement(Personal.account);// 账号
		acc.sendKeys(offer.getData("account", "tc2"));
		
		WebElement pwd = ad.findElement(Personal.password);// 密码
		pwd.sendKeys(offer.getData("password", "tc2"));
		
		WebElement loginBtn = ad.findElement(Personal.loginBtn);// 点击登录
		loginBtn.click();

	}
	
	
	public static void swipeToLeft() throws InterruptedException {

		System.out.println("执行：swipeToLeft");
		int width = ad.manage().window().getSize().width;
		int height = ad.manage().window().getSize().height;

		for (int i = 1; i <= 3; i++) {
			ad.swipe(width * 9 / 10, height / 2, width / 10, height / 2, 500);
			System.out.println("向左滑动了" + i + "次");
			Thread.sleep(1000);
		}
	}
	
	
	
	@AfterClass
	public void quit(){
		ad.quit();
	}
	
}

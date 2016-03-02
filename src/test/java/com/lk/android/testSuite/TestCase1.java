package com.lk.android.testSuite;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.lk.android.pages.Personal;
import com.lk.android.utils.AndroidInit;
import com.lk.android.utils.ExcelDataReader;

public class TestCase1 extends AndroidInit{
	
	public ExcelDataReader reader;
	
	@BeforeSuite
	public void init() throws MalformedURLException{
		super.initDriver();
		reader = new ExcelDataReader(System.getProperty("user.dir")+"/assets/data.xls");
	}
	
	@Test
	public void test_welcome() throws InterruptedException {
		System.out.println("开始执行：a_welcome()");
		Thread.sleep(5000);
		ad.swipeToLeft(3);
		ad.findElement(By.name("立即开启")).click();
		ad.findElement(By.name("我")).click();
		ad.findElement(By.name("我")).click();
		ad.findElement(By.name("我")).click();
		ad.findElement(By.name("点击登录")).click();
		Thread.sleep(2000);
	}
	
	@Test(description="正确的用户名、错误的密码")
	public void test_login(){
		System.out.println("开始执行：test_login()");
		
		WebElement acc = ad.findElement(Personal.account);// 账号
		acc.sendKeys(reader.getDataFromExcel("login","account","tc1"));
		
		WebElement pwd = ad.findElement(Personal.password);// 密码
		pwd.sendKeys(reader.getDataFromExcel("login","password","tc1"));
		
		WebElement loginBtn = ad.findElement(Personal.loginBtn);// 点击登录
		loginBtn.click();

	}
	
	@Test(description="正确的用户名、正确的密码")
	public void test_login2() throws InterruptedException{
		System.out.println("开始执行：test_login2()");
		
		WebElement acc = ad.findElement(Personal.account);// 账号
		acc.sendKeys(reader.getDataFromExcel("login", "account","tc2"));
		
		WebElement pwd = ad.findElement(Personal.password);// 密码
		pwd.sendKeys(reader.getDataFromExcel("login","password","tc2"));
		
		WebElement loginBtn = ad.findElement(Personal.loginBtn);// 点击登录
		loginBtn.click();
		
		Thread.sleep(2000);

	}
	
	
	@AfterSuite
	public void quit(){
		System.out.println("测试结束，正在清理数据...");
		ad.quit();
	}
	
}

package com.lk.android.testSuite;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lk.android.pages.Personal;
import com.lk.android.utils.AndroidDriverPlus;
import com.lk.android.utils.AndroidInit;
import com.lk.android.utils.CsvDataReader;
import com.lk.android.utils.ExcelDataReader;

public class TestCase2 extends AndroidInit{
	
	public ExcelDataReader reader;
	
	@BeforeClass
	public void init() throws MalformedURLException{
		super.initDriver();
		reader = new ExcelDataReader(System.getProperty("user.dir")+"/assets/data.xls");
	}
	
	@Test
	public void a_welcome() throws InterruptedException {
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
	public void t_login(){
		System.out.println("开始执行：login()");
		
		WebElement acc = ad.findElement(Personal.account);// 账号
		acc.sendKeys(reader.getDataFromExcel("login","account","tc1"));
		
		WebElement pwd = ad.findElement(Personal.password);// 密码
		pwd.sendKeys(reader.getDataFromExcel("login","password","tc1"));
		
		WebElement loginBtn = ad.findElement(Personal.loginBtn);// 点击登录
		loginBtn.click();

	}
	
	@Test(description="正确的用户名、正确的密码")
	public void t_login2(){
		System.out.println("开始执行：login()");
		
		WebElement acc = ad.findElement(Personal.account);// 账号
		acc.sendKeys(reader.getDataFromExcel("login", "account","tc2"));
		
		WebElement pwd = ad.findElement(Personal.password);// 密码
		pwd.sendKeys(reader.getDataFromExcel("login","password","tc2"));
		
		WebElement loginBtn = ad.findElement(Personal.loginBtn);// 点击登录
		loginBtn.click();

	}
	
	
	@AfterClass
	public void quit(){
		ad.quit();
	}
	
}

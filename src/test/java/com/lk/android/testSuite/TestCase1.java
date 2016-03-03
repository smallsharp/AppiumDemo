package com.lk.android.testSuite;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.lk.android.actions.Login;
import com.lk.android.utils.AndroidInit;
import com.lk.android.utils.ExcelReader;

/**
 * @author Administrator
 * @简介：登录、登出
 */

public class TestCase1 extends AndroidInit{
	
	public ExcelReader excel;
	
	@BeforeClass
	public void init() throws MalformedURLException{
		super.initDriver();
		excel = new ExcelReader(System.getProperty("user.dir")+"/assets/data.xls");
	}
	
	@Test
	public void test_welcome() throws InterruptedException{
		System.out.println("开始执行：test_welcome()");
		
		welcome.run();

	}
	
	@Test(description="正确的用户名、错误的密码")
	public void test_loginWrong() throws InterruptedException{
		
		System.out.println("开始执行：test_loginWrong()");
		boolean result = false;
		ad.findElement(By.name("我")).click();
		ad.findElement(By.name("点击登录")).click();
		Login login = new Login(ad);
		login.run(excel.getValue("login","account","tc1"), excel.getValue("login","password","tc1"));
		
		//错误的账号密码应该登录不成功，如果登录成功，则页面跳转到main
		Thread.sleep(3000);
		System.out.println("currentActivity:"+ad.currentActivity());
		if(!ad.currentActivity().equals(".MainActivity")){
			result = true;
		}
		Assert.assertTrue(result);
	}
	
	
	@Test(description="正确的用户名、正确的密码")
	public void test_loginRight() throws InterruptedException{
		
		System.out.println("开始执行：test_loginRight()");
		boolean result = false;
		Login login = new Login(ad);
		login.run(excel.getValue("login","account","tc2"), excel.getValue("login","password","tc2"));
		
		Thread.sleep(3000);
		System.out.println("currentActivity:"+ad.currentActivity());
		if(ad.currentActivity().equals(".MainActivity")){
			result = true;
		}
		Assert.assertTrue(result);
	}
	
	@Test(description="退出登录")
	public void test_loginOut() throws InterruptedException{
		
		System.out.println("开始执行：test_loginOut()");
		boolean result = false;
		
		ad.swipeUpUntilFind("设置");
		ad.findElement(By.name("设置")).click();
		ad.findElement(By.name("退出登录")).click();
		ad.findElement(By.name("是")).click();
		
		if(ad.findElement(By.name("点击登录")).isDisplayed()){
			result = true;
		}
		
		Assert.assertTrue(result);
	}
	
	
	@AfterClass
	public void quit(){
		System.out.println("测试结束，正在清理数据...");
		ad.quit();
	}
	
}
